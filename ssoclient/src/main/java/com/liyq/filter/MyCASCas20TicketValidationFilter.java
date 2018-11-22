package com.liyq.filter;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;

@WebFilter(asyncSupported=true, filterName="filter2_Cas20TicketValidationFilter", urlPatterns={"/login", "/db", "/redis"}, //"/*"
initParams={//@WebInitParam(name = "acceptAnyProxy", value = ""), 
		//@WebInitParam(name = "allowedProxyChains", value = ""), 
		@WebInitParam(name = "casServerLoginUrl", value = "http://172.20.13.123:808/cas/login?mode=browser&subpath=project4/liyingqiao"),
		@WebInitParam(name = "redirectAfterValidation", value = "true"), 
		@WebInitParam(name = "serverName", value = "http://172.20.13.123:8082"), 
		@WebInitParam(name = "casServerUrlPrefix", value = "http://172.20.13.123:808/cas"), 
		//@WebInitParam(name = "proxyCallbackUrl", value = ""), 
		//@WebInitParam(name = "renew", value = "")
		},
dispatcherTypes= {DispatcherType.ERROR, DispatcherType.FORWARD, 
		DispatcherType.INCLUDE, DispatcherType.REQUEST
})
public final class MyCASCas20TicketValidationFilter extends Cas20ProxyReceivingTicketValidationFilter {
}
