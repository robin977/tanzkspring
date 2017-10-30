package com.tanzk.bean.context;

import com.tanzk.bean.BeanDefinition;
import com.tanzk.bean.beanFactory.AbstractBeanFactory;
import com.tanzk.bean.beanFactory.AutowireCapableBeanFactory;
import com.tanzk.bean.io.ResourceLoader;
import com.tanzk.bean.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/28
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation,AbstractBeanFactory abstractBeanFactory) {
        super(abstractBeanFactory);
        this.configLocation=configLocation;
    }

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        super(new AutowireCapableBeanFactory());
        this.configLocation=configLocation;
        refresh();
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader=new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }
}
