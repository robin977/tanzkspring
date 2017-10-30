package com.tanzk.aop.proxy;

import com.tanzk.aop.AdvisedSupport;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/29
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy {

    @Override
    public Object getProxy() {
        return createProxy().getProxy();
    }

    public AopProxy createProxy(){
       return new Cglib2AopProxy(this);
    }
}
