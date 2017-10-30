package com.tanzk.aop;

import java.lang.reflect.Method;

/**
 * <p>功能描述：</p>
 * 方法匹配器
 * @author robin
 * @date 2017/10/28
 */
public interface MethodMatcher {
    boolean matches(Method method, Class targetClass);
}
