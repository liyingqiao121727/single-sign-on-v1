/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/index.html
 */
package com.liyq.utils.jasig;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.util.AbstractConfigurationFilter;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.validation.Assertion;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

/**
 * Implementation of a filter that wraps the normal HttpServletRequest with a
 * wrapper that overrides the following methods to provide data from the
 * CAS Assertion:
 * <ul>
 * <li>{@link HttpServletRequest#getUserPrincipal()}</li>
 * <li>{@link HttpServletRequest#getRemoteUser()}</li>
 * <li>{@link HttpServletRequest#isUserInRole(String)}</li>
 * </ul>
 * <p/>
 * This filter needs to be configured in the chain so that it executes after
 * both the authentication and the validation filters.
 *
 * @author Scott Battaglia
 * @author Marvin S. Addison
 * @version $Revision: 11729 $ $Date: 2007-09-26 14:22:30 -0400 (Tue, 26 Sep 2007) $
 * @since 3.0
 */
public class HttpServletRequestWrapperFilter extends AbstractConfigurationFilter {

    /** Name of the attribute used to answer role membership queries */
    private String roleAttribute;
   
    /** Whether or not to ignore case in role membership queries */
    private boolean ignoreCase;

    public void destroy() {
        // nothing to do
    }

    /**
     * Wraps the HttpServletRequest in a wrapper class that delegates
     * <code>request.getRemoteUser</code> to the underlying Assertion object
     * stored in the user session.
     */
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        final AttributePrincipal principal = retrievePrincipalFromSessionOrRequest(servletRequest);

        filterChain.doFilter(new CasHttpServletRequestWrapper((HttpServletRequest) servletRequest, principal), servletResponse);
    }

    protected AttributePrincipal retrievePrincipalFromSessionOrRequest(final ServletRequest servletRequest) {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpSession session = request.getSession(false);
        final Assertion assertion = (Assertion) (session == null ? request.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION) : session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION));

        return assertion == null ? null : assertion.getPrincipal();
    }

    public void init(final FilterConfig filterConfig) throws ServletException {
        this.roleAttribute = getPropertyFromInitParams(filterConfig, "roleAttribute", null);
        this.ignoreCase = Boolean.parseBoolean(getPropertyFromInitParams(filterConfig, "ignoreCase", "false"));
    }

    final class CasHttpServletRequestWrapper extends HttpServletRequestWrapper {

        private final AttributePrincipal principal;

        CasHttpServletRequestWrapper(final HttpServletRequest request, final AttributePrincipal principal) {
            super(request);
            this.principal = principal;
        }

        public Principal getUserPrincipal() {
            return this.principal;
        }

        public String getRemoteUser() {
            return principal != null ? this.principal.getName() : null;
        }

        public boolean isUserInRole(final String role) {
            if (CommonUtils.isBlank(role)) {
                log.debug("No valid role provided.  Returning false.");
                return false;
            }

            if (this.principal == null) {
                log.debug("No Principal in Request.  Returning false.");
                return false;
            }

            if (CommonUtils.isBlank(roleAttribute)) {
                log.debug("No Role Attribute Configured. Returning false.");
                return false;
            }

            final Object value = this.principal.getAttributes().get(roleAttribute);
            
            if (value instanceof Collection) {
                for (final Iterator iter = ((Collection) value).iterator(); iter.hasNext();) {
                    if (rolesEqual(role, iter.next())) {
                        log.debug("User [" + getRemoteUser() + "] is in role [" + role + "]: " + true);
                        return true;
                    }
                }
            }

            final boolean isMember = rolesEqual(role, value);
            log.debug("User [" + getRemoteUser() + "] is in role [" + role + "]: " + isMember);
            return isMember;
        }
        
        /**
         * Determines whether the given role is equal to the candidate
         * role attribute taking into account case sensitivity.
         *
         * @param given  Role under consideration.
         * @param candidate Role that the current user possesses.
         *
         * @return True if roles are equal, false otherwise.
         */
        private boolean rolesEqual(final String given, final Object candidate) {
            return ignoreCase ? given.equalsIgnoreCase(candidate.toString()) : given.equals(candidate);
        }
    }
}
