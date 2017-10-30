package com.tanzk.aop.proxy;

import org.aopalliance.aop.Advice;

/**
 * <p>功能描述：</p>
 * apspectj表达式切面增强实现类
 * @author robin
 * @date 2017/10/29
 */
public class AspectJExpressionPointcutAdvisor implements PointCutAdvisor {

    private AspectJExpressionPointcut pointcut=new AspectJExpressionPointcut();

    //由配置文件注入
    private  Advice advice;

    //由配置文件注入
    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }


    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public PointCut getPointCut() {
        return pointcut;
    }
}
