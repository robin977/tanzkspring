package com.tanzk.aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * <p>功能描述：</p>
 *相当于advice
 * @author robin
 * @date 2017/10/28
 */
public class TimerInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("before");
        Object object= methodInvocation.proceed();
        System.out.println("after");
        return object;
    }
}
