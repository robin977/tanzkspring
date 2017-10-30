package com.tanzk.aop.proxy;

import com.tanzk.aop.TargetSource;
import com.tanzk.aop.interceptor.TimerInterceptor;
import com.tanzk.bean.BeanPostProcessor;
import com.tanzk.bean.aware.BeanFactoryAware;
import com.tanzk.bean.beanFactory.AbstractBeanFactory;
import com.tanzk.bean.beanFactory.BeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * <p>功能描述：</p>
 * aop织入代理生成器
 * @author robin
 * @date 2017/10/29
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanFactoryAware,BeanPostProcessor {

    private AbstractBeanFactory beanFactory;


    //前置处理器
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    //后置处理器
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {

        if(bean instanceof AspectJExpressionPointcutAdvisor){
            return  bean;
        }

        if(bean instanceof TimerInterceptor){
            return  bean;
        }
        System.out.println("postProcessAfterInitialization:"+beanName+",class:"+bean.getClass());
        List<AspectJExpressionPointcutAdvisor> advisors= beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
        for(AspectJExpressionPointcutAdvisor advisor:advisors){
            //符合类条件
            if(advisor.getPointCut().getClassFilter().matches(bean.getClass())){
               //生成代理工厂类
              ProxyFactory proxyFactory=new ProxyFactory();
              proxyFactory.setMethodInterceptor((MethodInterceptor)advisor.getAdvice());
              proxyFactory.setMethodMatcher(advisor.getPointCut().getMethodMatcher());
              proxyFactory.setTargetSource(new TargetSource(bean,bean.getClass(),bean.getClass().getInterfaces()));
                System.out.println(proxyFactory.getProxy().getClass().getName());
              return  proxyFactory.getProxy();
            }
        }
        return bean;
    }



    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
       this.beanFactory=(AbstractBeanFactory)beanFactory;
    }
}
