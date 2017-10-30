package com.tanzk.aop.proxy;

import com.tanzk.aop.AdvisedSupport;
import com.tanzk.aop.ReflectiveMethodInvocation;
import com.tanzk.aop.proxy.AbstractAopProxy;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/28
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object getProxy() {
        return  Proxy.newProxyInstance(getClass().getClassLoader(),advisedSupport.getTargetSource().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object=null;
        MethodInterceptor methodInterceptor=advisedSupport.getMethodInterceptor();
        if(advisedSupport.getMethodMatcher()!=null&& advisedSupport.getMethodMatcher().matches(method,advisedSupport.getTargetSource().getTargetClass())){
            object= methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,args));
         }else{
            object= method.invoke(advisedSupport.getTargetSource().getTarget(),args);
        }

        return object;
    }
}
