package com.learning.zuul.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by Mocha on 2019/1/15.
 */
@Aspect
@Component
public class LogOperateAspect {

    @Pointcut("@annotation(com.learning.zuul.log.LogOperate)")
    public  void annotationPointCut() {
    }

    @Before("annotationPointCut()")
    public void before(JoinPoint joinpoint) {
        Object[] objs = joinpoint.getArgs();//方法入参
        String className = joinpoint.getTarget().getClass().toString();//类名
        MethodSignature methodSignature = (MethodSignature)joinpoint.getSignature();
        Method method = methodSignature.getMethod();//方法
        LogOperate logOperate = method.getAnnotation(LogOperate.class);
        System.out.println(String.format("classname -> %s method -> %s desc ->%s ", className, method.getName(), logOperate.desc()));
    }


}
