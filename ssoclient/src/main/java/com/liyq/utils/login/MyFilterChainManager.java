package com.liyq.utils.login;

import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;

public class MyFilterChainManager {

	public static void createChain(DefaultFilterChainManager fc) {
		Map<String, Filter> map = fc.getFilters();
		//anon=anon, authc=authc, authcBasic=authcBasic, logout=logout, 
		//noSessionCreation=noSessionCreation, perms=perms, port=port, 
		//rest=rest, roles=roles, ssl=ssl, user=user
		FormAuthenticationFilter faf = (FormAuthenticationFilter)map.get("authc");
		System.out.println(map.get("authc").getClass());
		UserFilter uf = (UserFilter) map.get("user");
		fc.setFilters(map);
		LogoutFilter lf = (LogoutFilter) fc.getFilter("logout");
		lf.setRedirectUrl("/");
		uf.setLoginUrl("/");
		faf.setLoginUrl("http://127.0.0.1/project/");
		fc.createChain("/redis", "authc");
		fc.createChain("/db", "anon");
		fc.createChain("/login", "anon");
		fc.createChain("/logindemo", "anon");
		fc.createChain("/logout", "logout");
	}
}
