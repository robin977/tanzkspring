package com.tanzk.bean;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/27
 */
public class BeanDefinition {
    private Class beanClass;
    private String beanClassName;
    private Object bean;


    private PropertyValues propertyValues;
    public BeanDefinition() {
        propertyValues=new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
            try {
                this.beanClass=Class.forName(beanClassName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

}
