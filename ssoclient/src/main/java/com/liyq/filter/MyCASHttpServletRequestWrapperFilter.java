package com.liyq.filter;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;

import com.liyq.utils.jasig.HttpServletRequestWrapperFilter;

@WebFilter(asyncSupported=true, filterName="filter3_HttpServletRequestWrapperFilter", urlPatterns={"/login", "/db", "/redis"}, //"/*"
dispatcherTypes= {DispatcherType.ERROR, DispatcherType.FORWARD, 
		DispatcherType.INCLUDE, DispatcherType.REQUEST
})
public final class MyCASHttpServletRequestWrapperFilter extends HttpServletRequestWrapperFilter {

}
