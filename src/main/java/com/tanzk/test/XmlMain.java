package com.tanzk.test;

import com.tanzk.bean.context.ClassPathXmlApplicationContext;
import com.tanzk.entity.User;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/27
 */
public class XmlMain {


    public static void main(String[] args) throws Exception {
//        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
//        xmlBeanDefinitionReader.loadBeanDefinitions("spring.xml");

// 2.初始化BeanFactory并注册bean
//        AutowireCapableBeanFactory beanFactory = new AutowireCapableBeanFactory();
//        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
//            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
//        }

        ClassPathXmlApplicationContext classPathXmlApplicationContext=new ClassPathXmlApplicationContext("spring.xml");

        // 3.获取bean
        User user = (User) classPathXmlApplicationContext.getBean("user");
        user.desc();
    }
}
