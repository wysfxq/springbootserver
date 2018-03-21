package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//Configuration相当于.xml配置文件
//@ComponentScan("com.example")//扫描包(如果controller包不与DemoApplication不在同一层则需要扫描包)
//@ImportResource(value = {"classpath:dubboproviders.xml"}) // 使用 providers.xml 配置;
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        
    }
}
