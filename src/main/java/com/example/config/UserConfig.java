package com.example.config;

import com.example.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yinsheng.wang on 2018/1/18.
 */
@Configuration
public class UserConfig {
    @Bean(name = "user111111111111111111111111")
    public User user() {
        return new User();
    }
}
