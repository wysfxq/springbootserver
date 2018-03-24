package com.example.config;

import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.dangdang.ddframe.rdb.sharding.id.generator.self.CommonSelfIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yinsheng.wang on 2018/3/24.
 */
@Configuration
public class IdGeneratorConfig {
    @Bean
    public IdGenerator getIdGenerator() {
        return new CommonSelfIdGenerator();
    }
}
