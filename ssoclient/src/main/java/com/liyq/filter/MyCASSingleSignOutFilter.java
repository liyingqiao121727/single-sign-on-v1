package com.liyq.filter;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;

import com.liyq.utils.jasig.SingleSignOutFilter;

@WebFilter(asyncSupported=true, filterName="filter0_SingleSignOutFilter", urlPatterns={"/login", "/db", "/redis"}, //"/*" 
dispatcherTypes= {DispatcherType.ERROR, DispatcherType.FORWARD, 
		DispatcherType.INCLUDE, DispatcherType.REQUEST
})
public final class MyCASSingleSignOutFilter extends SingleSignOutFilter {

}
