<%@page import="java.util.Base64"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<body>
<h1>hello world demo1</h1>
<c:if test="${not pageContext.request.secure}">
	<div id="msg" class="errors">
		<h2>Non-secure Connection</h2>
		<p>You are currently accessing CAS over a non-secure connection.
			Single Sign On WILL NOT WORK. In order to have single sign on work,
			you MUST log in over HTTPS.</p>
	</div>
</c:if>

<div class="box" id="login">
	<%-- <form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true">

    <form:errors path="*" id="msg" cssClass="errors" element="div" htmlEscape="false" />
  
    <h2><spring:message code="screen.welcome.instructions" /></h2> --%>
	<form method="post">
		<section class="row">
			<label for="username">username</label>
			<input type="text" id="username" name="username" />
		</section>

		<section class="row">
			<label for="password">password：</label>
			<%--
      NOTE: Certain browsers will offer the option of caching passwords for a user.  There is a non-standard attribute,
      "autocomplete" that when set to "off" will tell certain browsers not to prompt to cache credentials.  For more
      information, see the following web page:
      http://www.technofundo.com/tech/web/ie_autocomplete.html
      --%>
			<input type="password" id="password" name="password" />
		</section>

		<section class="row check">
			<input id="warn" name="warn" value="true" tabindex="3"
				type="checkbox" /> <label for="warn">warning</label>
		</section>

		<section class="row btn-row">
			<input type="hidden" name="lt" value="<%=request.getParameter("lt")%>" /> 
			<input type="hidden" name="execution" value="<%=request.getParameter("execution")%>" />
			<input type="hidden" name="_eventId" value="submit" /> 
			
			<input class="btn-submit" name="submit" accesskey="l" value="login" tabindex="4" type="submit" /> 
			<input class="btn-reset" name="reset" accesskey="c" value="clear" tabindex="5" type="reset" />
		</section>
	</form>
</div>

<div id="sidebar">
	<div class="sidebar-content">
		<p>Welcome</p>

		<div id="list-languages">
			<%
				final String queryString = request.getQueryString() == null
						? ""
						: request.getQueryString().replaceAll(
								"&locale=([A-Za-z][A-Za-z]_)?[A-Za-z][A-Za-z]|^locale=([A-Za-z][A-Za-z]_)?[A-Za-z][A-Za-z]",
								"");
			%>
			<c:set var='query' value='<%=queryString%>' />
			<c:set var="xquery" value="${fn:escapeXml(query)}" />

			<h3>Languages:</h3>

			<c:choose>
				<c:when
					test="${not empty requestScope['isMobile'] and not empty mobileCss}">
					<form method="get" action="login?${xquery}">
						<select name="locale">
							<option value="en">English</option>
							<option value="zh_CN">Chinese (Simplified)</option>
							<option value="zh_TW">Chinese (Traditional)</option>
						</select> <input type="submit" value="Switch">
					</form>
				</c:when>
				<c:otherwise>
					<c:set var="loginUrl"
						value="login?${xquery}${not empty xquery ? '&' : ''}locale=" />
					<ul>
						<li class="first"><a href="${loginUrl}en">English</a></li>
						<li class="last"><a href="${loginUrl}pl">Polish</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" charset="UTF-8" defer="defer">
var errors = '<%=request.getParameter("errors") %>';
var username = '<%=request.getParameter("username") %>';
load();

function load() {
	if (errors != 'null') {
		//errors = window.atob(errors);
		var username = document.getElementById("username");
	    username.value = errors;
	    errors = '';
	}
}

</script>