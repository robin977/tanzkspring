package com.tanzk.bean.aware;

import com.tanzk.bean.beanFactory.BeanFactory;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/27
 */
public interface BeanFactoryAware {

    public void setBeanFactory(BeanFactory beanFactory);
}
