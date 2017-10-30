package com.tanzk.test;

import com.tanzk.bean.BeanDefinition;
import com.tanzk.bean.BeanReference;
import com.tanzk.bean.PropertyValue;
import com.tanzk.bean.PropertyValues;
import com.tanzk.bean.beanFactory.AutowireCapableBeanFactory;
import com.tanzk.entity.User;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/27
 */
public class Main {


    public static void main(String[] args) throws Exception {
        AutowireCapableBeanFactory beanFactory=new AutowireCapableBeanFactory();
        BeanDefinition beanDefinition1=new BeanDefinition();
        beanDefinition1.setBeanClassName("com.tanzk.test.User");

        BeanDefinition beanDefinition2=new BeanDefinition();
        beanDefinition2.setBeanClassName("com.tanzk.test.Addr");

        BeanReference beanReference=new BeanReference("addr");
        beanReference.setBean(beanDefinition2);

        PropertyValues upropertyValues = new PropertyValues();
        upropertyValues.addPropertyValue(new PropertyValue("name","robin"));
        upropertyValues.addPropertyValue(new PropertyValue("age",new Integer(33)));
        upropertyValues.addPropertyValue(new PropertyValue("addr",beanReference));
        beanDefinition1.setPropertyValues(upropertyValues);


        beanFactory.registerBeanDefinition("user",beanDefinition1);
        //这个注册名必须与beanReference相同
        beanFactory.registerBeanDefinition("addr",beanDefinition2);
        User user= (User) beanFactory.getBean("user");
        user.desc();
    }
}
