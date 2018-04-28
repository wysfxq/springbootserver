package com.example.annotation;

import java.lang.annotation.*;

/**
 * Created by yinsheng.wang on 2018/4/28.
 */
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CheckParam {
}
