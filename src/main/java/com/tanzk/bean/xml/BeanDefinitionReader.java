package com.tanzk.bean.xml;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/27
 */
public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws Exception;
}
