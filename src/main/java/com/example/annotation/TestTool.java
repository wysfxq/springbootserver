package com.example.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by yinsheng.wang on 2018/5/4.
 */
public class TestTool {
    public static void main(String[] args) {
        NoBug testobj = new NoBug();
        Class clazz = testobj.getClass();
        if (clazz.isAnnotationPresent(WysWrite.class)) {
            System.out.println("is wyswrite");
        }

        Method[] method = clazz.getDeclaredMethods();

        //用来记录测试产生的 log 信息
        StringBuilder log = new StringBuilder();
        // 记录异常的次数
        int errornum = 0;
        for (Method m : method) {
            if (m.isAnnotationPresent(WysWrite.class)) {
                try {
                    m.setAccessible(true);//类中的成员变量为private,须进行此操作

                    Object[] obj = m.getParameters();

                    //获取方法参数中的注解
                    Annotation[][] parameterAnnotations = m.getParameterAnnotations();
                    for (int i = 0; i < parameterAnnotations.length; i++) {
                        Annotation[] annotations = parameterAnnotations[i];
                        for (int j = 0; j < annotations.length; j++) {
                            if (annotations[j].annotationType().equals(NotNull.class)) {
                                if (obj[i] == null) {
                                    System.out.println("aaa");
                                }
                            }
                        }
                    }
                    m.invoke(testobj, null);
                } catch (Exception e) {
                    errornum++;
                    log.append(m.getName());
                    log.append(" ");
                    log.append("has error:");
                    log.append("\n\r  caused by ");
                    //记录测试过程中，发生的异常的名称
                    log.append(e.getCause().getClass().getSimpleName());
                    log.append("\n\r");
                    //记录测试过程中，发生的异常的具体信息
                    log.append(e.getCause().getMessage());
                    log.append("\n\r");
                }
            }
        }
        log.append(clazz.getSimpleName());
        log.append(" has  ");
        log.append(errornum);
        log.append(" error.");

        // 生成测试报告
        System.out.println(log.toString());
    }
}
