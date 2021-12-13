package com.afei.redis.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRedissonConfig {
    @Bean
    public RedissonClient redisson() {
        // 单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://120.26.62.128:6379").setDatabase(0);
        return Redisson.create(config);
    }
}
