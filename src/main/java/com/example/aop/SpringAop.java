package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by yinsheng.wang on 2018/4/28.
 */
@Aspect//描述一个切面类，定义切面类的时候需要打上这个注解
@Component
public class SpringAop {
   /* @Pointcut("execution(* com.example.service.*.*(..))")
    public void qieru(){

    }
    @Before("qieru()")
    public void qianzhi(JoinPoint joinPoint){
        System.out.println(joinPoint);
        System.out.println("前置通知");
    }*/


/*    @Around("qieru()")
    public void huanrao(ProceedingJoinPoint poin){
        System.out.println("环绕通知");
    }
    @Before("qieru()")
    public void qianzhi(){
        System.out.println("前置通知");
    }
    @After("qieru()")
    public void houzhi(){
        System.out.println("后置通知");
    }

    @AfterReturning("qieru()")
    public void qianzhifanhui(){
        System.out.println("后置返回 ");
    }
    @AfterThrowing("qieru()")
    public void qianzhiYichang(){
        System.out.println("后置异常");
    }*/
}
