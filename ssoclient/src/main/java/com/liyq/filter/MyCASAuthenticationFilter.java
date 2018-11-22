package com.liyq.filter;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.jasig.cas.client.authentication.AuthenticationFilter;

/**
 * you can config it on web.xml to replace it(annotation).
 * 
 * @author Liyingqiao
 *
 */
@WebFilter(asyncSupported=true, filterName="filter1_AuthenticationFilter", urlPatterns={"/login", "/db", "/redis"}, //"/*"
initParams={@WebInitParam(name = "casServerLoginUrl", 
value = "http://172.20.13.123:808/cas/login?mode=browser&subpath=ssoclient/liyingqiao"), //com.liyq.controller 's url
		@WebInitParam(name = "serverName", value = "http://172.20.13.123:8082"), 
		},
dispatcherTypes= {DispatcherType.ERROR, DispatcherType.FORWARD, 
		DispatcherType.INCLUDE, DispatcherType.REQUEST
})
public final class MyCASAuthenticationFilter extends AuthenticationFilter {
}
