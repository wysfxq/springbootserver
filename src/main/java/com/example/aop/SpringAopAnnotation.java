package com.example.aop;

import com.example.annotation.Examine;
import com.example.annotation.NotNull;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by yinsheng.wang on 2018/4/28.
 */
@Aspect//描述一个切面类，定义切面类的时候需要打上这个注解
@Component
public class SpringAopAnnotation {
    //定义切入点
    @Pointcut("@annotation(com.example.annotation.Examine)")
    private void anyMethod() {
    }

    @Before("anyMethod()")
    public void qianzhi(JoinPoint joinPoint) {
      /*  System.out.println(joinPoint);
        System.out.println("前置通知11111");*/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();//方法规则式拦截
        int parameterCount = method.getParameterCount();
        if (parameterCount == 0) {
            return;
        }
        // Annotation[] myAnnotation = method.getAnnotations();
        //查看某个方法是否存在该注解
        if (method.isAnnotationPresent(Examine.class)) {
            Examine myAnnotation = method.getAnnotation(Examine.class);
            /*String hello = myAnnotation.hello();
            String world = myAnnotation.world();
            System.out.println(hello +", " +world);*/
            String str = myAnnotation.value();
            System.out.println(str);
        }
        //得到方法的所有注解，并遍历
        Annotation[] Methodannotations = method.getAnnotations();
        for (Annotation a : Methodannotations) {
            System.out.println(a);
        }

        Object[] args = joinPoint.getArgs();
        Class[] parameterTypes = signature.getParameterTypes();
        //获取方法参数中的注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            for (int j = 0; j < annotations.length; j++) {
                if (annotations[j].annotationType().equals(NotNull.class) && args[i] == null) {
                    System.out.println("参数为空");
                }
            }
        }
    }

}
