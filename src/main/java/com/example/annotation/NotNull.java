package com.example.annotation;

/**
 * Created by yinsheng.wang on 2018/4/28.
 */

import java.lang.annotation.*;

/**
 * 定义非空类 或者 类的非空字段，用来入参的非空校验
 *
 * @author: SunKuo
 * Created in 16:38 on 2017/12/6.
 */
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface NotNull {
}
