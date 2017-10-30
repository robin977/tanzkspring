package com.tanzk.aop.proxy;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/29
 */
public interface PointCutAdvisor extends Advisor {
    PointCut getPointCut();
}
