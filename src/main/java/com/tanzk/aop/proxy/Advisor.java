package com.tanzk.aop.proxy;

import org.aopalliance.aop.Advice;

/**
 * <p>功能描述：</p>
 * 持有AOP advice
 * @author robin
 * @date 2017/10/29
 */
public interface Advisor {
    /**
     * Advice是AOP编程中某一个方面(Aspect)在某个连接点(JoinPoint)所执行的特定动作
     * MethodInterceptor->Interceptor->advice 子类同为aop联盟规定
     * @return
     */
    Advice getAdvice();
}
