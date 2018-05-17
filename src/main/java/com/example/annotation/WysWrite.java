package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yinsheng.wang on 2018/5/4.
 */
@Target({ElementType.METHOD, ElementType.PARAMETER}) //注解到方法上
@Retention(RetentionPolicy.RUNTIME) //运行时起作用
public @interface WysWrite {
}
