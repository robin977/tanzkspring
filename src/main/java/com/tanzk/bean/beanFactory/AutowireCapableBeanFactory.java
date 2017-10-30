package com.tanzk.bean.beanFactory;

import com.tanzk.bean.BeanDefinition;
import com.tanzk.bean.BeanReference;
import com.tanzk.bean.PropertyValue;
import com.tanzk.bean.aware.BeanClassLoaderAware;
import com.tanzk.bean.aware.BeanFactoryAware;
import com.tanzk.bean.aware.BeanNameAware;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>功能描述：</p>
 * 可自动装配内容的BeanFactory
 * @author robin
 * @date 2017/10/27
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {



    //初始化 判断BeanFactoryProcessor
//    protected Object initializeBean(final String beanName, final Object bean, BeanDefinition mbd) {
//
//        return null;
//    }


    //调用aware
    protected Object invokeAwareMethod(final String beanName, final Object bean) {
        if(bean instanceof BeanFactoryAware){
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        if(bean instanceof BeanNameAware) {
            ((BeanNameAware)bean).setBeanName(beanName);
        }
        if(bean instanceof BeanClassLoaderAware) {
            ((BeanClassLoaderAware)bean).setBeanClassLoader(this.getBeanClassLoader());
        }
        return bean;
    }


    @Override
    public void applyPropertyValues(final String beanName,Object bean, BeanDefinition beanDefinition)  throws Exception{
        super.applyPropertyValues(beanName,bean, beanDefinition);
        invokeAwareMethod(beanName,bean);
        if(beanDefinition.getPropertyValues()==null) {
            return;
        }

        for(PropertyValue propertyValue:beanDefinition.getPropertyValues().getPropertyValueList()){
            Object value= propertyValue.getValue();
            if(value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                //引用对象的名称
                String name = beanReference.getName();
                //具体的引用对象
                value = getBean(name);
            }
            try {
            //取得方法
            Method declaredMethod= bean.getClass().getDeclaredMethod("set"+propertyValue.getName().substring(0,1).toUpperCase()+propertyValue.getName().substring(1),value.getClass());
                declaredMethod.invoke(bean,value);
            } catch (NoSuchMethodException e) {
                Field declaredField=bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean,value);
            }
        }

    }
}
