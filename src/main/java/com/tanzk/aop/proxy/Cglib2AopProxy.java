package com.tanzk.aop.proxy;

import com.tanzk.aop.AdvisedSupport;
import com.tanzk.aop.ClassFilter;
import com.tanzk.aop.ReflectiveMethodInvocation;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * <p>功能描述：</p>
 *cglib是针对类来进行代理的，他的原理是对指定的目标类生成一个子类，并覆盖其中方法增强，因为采用的是继承，所以不能对final修饰的类进行代理
 * cglib采用非常底层的字节码技术，其原理是通过字节码技术为一个类创建子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑
 * @author robin
 * @date 2017/10/29
 */
public class Cglib2AopProxy extends AbstractAopProxy {

    public Cglib2AopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object getProxy() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTargetClass());
        enhancer.setInterfaces(advisedSupport.getTargetSource().getInterfaces());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advisedSupport));
        Object object=enhancer.create();
        return object;
    }

    //继承cglib的方法拦截器
    private  static class DynamicAdvisedInterceptor implements MethodInterceptor {
        private AdvisedSupport advisedSupport;

        private org.aopalliance.intercept.MethodInterceptor delegate;

        public DynamicAdvisedInterceptor(AdvisedSupport advisedSupport) {
            this.advisedSupport = advisedSupport;
            delegate=advisedSupport.getMethodInterceptor();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] arguments, MethodProxy methodProxy) throws Throwable {

            if(advisedSupport.getMethodMatcher()==null || advisedSupport.getMethodMatcher().matches(method,advisedSupport.getTargetSource().getTargetClass())){
                return delegate.invoke(new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,arguments,methodProxy));
            }
            return new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,arguments,methodProxy).proceed();
        }


//        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            return methodInvocation.proceed();
        }
    }


    private  static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] arguments,MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy=methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(target,arguments);
        }
    }
}
