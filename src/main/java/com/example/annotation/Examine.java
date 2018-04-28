package com.example.annotation;

/**
 * Created by yinsheng.wang on 2018/4/28.
 */

import java.lang.annotation.*;

/**
 * 定义需要记录入参、结果数据，校验参数的方法
 *
 * @author: SunKuo
 * Created in 16:40 on 2017/12/6.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Examine {
    String value() default "no";
}
