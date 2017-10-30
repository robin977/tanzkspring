package com.tanzk.aop.proxy;

import com.tanzk.aop.ClassFilter;
import com.tanzk.aop.MethodMatcher;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/29
 */
public class AspectJExpressionPointcut implements PointCut,ClassFilter,MethodMatcher {
    //切面解析器
    private PointcutParser pointcutParser;

    //表达式
    private String expression;

    //切面表达式
    private PointcutExpression pointcutExpression;

    private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    static {
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }

    public AspectJExpressionPointcut() {
        this(DEFAULT_SUPPORTED_PRIMITIVES);
    }

    public AspectJExpressionPointcut(Set<PointcutPrimitive> pointcutPrimitives) {
       this.pointcutParser=PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(pointcutPrimitives);
    }


    public void setExpression(String expression) {
        this.expression = expression;
    }

    private PointcutExpression buildPointcutExpression() {
       return this.pointcutParser.parsePointcutExpression(expression);
    }

    protected void checkReadyToMatch() {
        if(pointcutExpression==null){
          this.pointcutExpression=  buildPointcutExpression();
        }
    }

    //匹配类
    @Override
    public boolean matches(Class targetClass) {
        checkReadyToMatch();
        return  pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    //匹配方法
    @Override
    public boolean matches(Method method, Class targetClass) {
        checkReadyToMatch();
        //如果没有指定方法，那么每个方法名都是符合条件的 如：
        //execution(* com.tanzk.entity.User.desc*(..)) 除了desc返回true,其他返回false
        //execution(* com.tanzk.entity.User.*.*(..))
        ShadowMatch shadowMatch=pointcutExpression.matchesMethodExecution(method);
        if(shadowMatch.alwaysMatches()){
            return true;
        }else if(shadowMatch.neverMatches()){
            return  false;
        }

        return false;
    }



    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
