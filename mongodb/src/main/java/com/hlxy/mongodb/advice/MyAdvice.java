package com.hlxy.mongodb.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAdvice {
    //<aop:pointcut expression="execution( * cn.zj.spring.service..*.*(..))" id="当前方法名就是id"/>
    @Pointcut("execution(* com.hlxy.mongodb.service..*.*(..))")
    public void mongodbAdvice(){}

    @Before("mongodbAdvice()")
    public void begin(){
        System.out.println("前置通知");
    }
    @After("mongodbAdvice()")
    public void after(){
        System.out.println("后置通知");
    }

    @AfterReturning("mongodbAdvice()")
    public void AfterReturning(){
        System.out.println("最终通知。报异常，就不执行");
    }

    @AfterThrowing(pointcut = "mongodbAdvice()",throwing="ex")
    public void AfterThrowing(Throwable ex){
        System.out.println("报异常时执行"+ex.getMessage());
    }

    @Around("mongodbAdvice()") //<aop:around method="allInOne" pointcut-ref="pt"/>
    public Object allInOne(ProceedingJoinPoint pjp) {
        Object result = null;
        try {
            System.out.println("开启事务------");
            //执行被代理对象当前需要执行业务方法
            result = pjp.proceed();
            System.out.println("提交事务------");
        } catch (Throwable ex) {
            System.out.println("------回滚事务 : " + ex.getMessage());
        }finally {
            System.out.println("关闭session------");
        }
        return result;
    }

}
