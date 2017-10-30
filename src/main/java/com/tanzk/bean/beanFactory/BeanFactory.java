package com.tanzk.bean.beanFactory;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/27
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;
}
