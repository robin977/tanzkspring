package com.tanzk.aop;

/**
 * <p>功能描述：</p>
 * 类过滤器
 * @author robin
 * @date 2017/10/29
 */
public interface ClassFilter {
    boolean matches(Class targetClass);
}
