package com.tanzk.aop;

/**
 * <p>功能描述：</p>
 * 被代理的对象
 * @author robin
 * @date 2017/10/28
 */
public class TargetSource {
    private Class<?> targetClass;
    private Class<?> [] interfaces;
    private Object    target;

    public TargetSource(Object target,Class<?> targetClass,Class<?>... interfaces) {
        this.targetClass = targetClass;
        this.interfaces = interfaces;
        this.target = target;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Class<?>[] interfaces) {
        this.interfaces = interfaces;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
