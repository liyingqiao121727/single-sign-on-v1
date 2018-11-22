package com.liyq.filter;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;

import com.liyq.utils.jasig.AssertionThreadLocalFilter;

@WebFilter(asyncSupported=true, filterName="filter4_HttpServletRequestWrapperFilter", urlPatterns={"/login", "/db", "/redis"}, //"/*"
dispatcherTypes= {DispatcherType.ERROR, DispatcherType.FORWARD, 
		DispatcherType.INCLUDE, DispatcherType.REQUEST
})
public class MyCASAssertionThreadLocalFilter extends AssertionThreadLocalFilter{
}
