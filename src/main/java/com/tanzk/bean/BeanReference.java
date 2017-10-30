package com.tanzk.bean;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/27
 */
public class BeanReference {
    private String name;
    private Object bean;


    public BeanReference() {
    }

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
