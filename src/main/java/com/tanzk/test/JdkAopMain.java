package com.tanzk.test;

import com.tanzk.aop.*;
import com.tanzk.aop.interceptor.TimerInterceptor;
import com.tanzk.aop.proxy.Cglib2AopProxy;
import com.tanzk.aop.proxy.JdkDynamicAopProxy;
import com.tanzk.bean.context.ClassPathXmlApplicationContext;
import com.tanzk.entity.IUser;
import com.tanzk.entity.User;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/27
 */
public class JdkAopMain {


    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext classPathXmlApplicationContext=new ClassPathXmlApplicationContext("spring.xml");
        // 3.获取bean
        IUser user = (User) classPathXmlApplicationContext.getBean("user");
        //user.desc();
        // 1. 设置被代理对象(Joinpoint)
       // AdvisedSupport advisedSupport = new AdvisedSupport();
       // TargetSource  targetSource=new TargetSource(user,User.class,IUser.class);
      //  advisedSupport.setTargetSource(targetSource);
        // 2. 设置拦截器(Advice)
      //  advisedSupport.setMethodInterceptor(new TimerInterceptor());
        // 3. 创建代理(Proxy)
      //  JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
      //  Cglib2AopProxy cglib2AopProxy = new Cglib2AopProxy(advisedSupport);
       // IUser userProxy = (IUser) cglib2AopProxy.getProxy();
        user.desc();
    }
}
