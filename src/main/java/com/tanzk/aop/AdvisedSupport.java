package com.tanzk.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/28
 */
public class AdvisedSupport {
    private TargetSource      targetSource;
    private MethodMatcher     methodMatcher;
    private MethodInterceptor methodInterceptor;

    public TargetSource getTargetSource() {

        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
}
