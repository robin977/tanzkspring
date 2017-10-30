package com.tanzk.aop.proxy;

import com.tanzk.aop.ClassFilter;
import com.tanzk.aop.MethodMatcher;

/**
 * <p>功能描述：</p>
 *切面
 * @author robin
 * @date 2017/10/29
 */
public interface PointCut {
    //通过pointcut表达式对类进行过滤
    ClassFilter getClassFilter();

    //通过pointcut表达式对方法进行过滤
    MethodMatcher getMethodMatcher();
}
