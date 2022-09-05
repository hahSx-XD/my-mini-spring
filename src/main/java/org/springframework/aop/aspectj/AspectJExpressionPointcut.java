package org.springframework.aop.aspectj;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Cai 🥬
 * @Date: 2022-09-05 10:47
 * @Version: 1.0
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {
    
    private static final Set<PointcutPrimitive> SUPPORT_PRIMITIVES =  new HashSet<>();
    
    static {
        SUPPORT_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }
    
    private final PointcutExpression pointcutExpression;
    
    public AspectJExpressionPointcut(String expression) {
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORT_PRIMITIVES, this.getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }
    
    @Override
    public boolean matches(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }
    
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
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
