package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by yinsheng.wang on 2018/4/17.
 */

@Configuration
@ImportResource({ "classpath:spring-dubbo.xml" })
public class DubboConfig {

}

