package com.tanzk.aop.proxy;

import com.tanzk.aop.AdvisedSupport;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/28
 */
public abstract class AbstractAopProxy implements AopProxy {
    protected AdvisedSupport advisedSupport;

    public AbstractAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }


}
