package com.study.cache.redis.a6_sentinel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * program: java-learn->SentinelRedisAppConfig
 * description: 哨兵配置
 * author: gerry
 * created: 2020-04-11 22:07
 **/
@Configuration
@Profile("sentinel")
public class SentinelRedisAppConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        System.out.println("使用哨兵版本");
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("mymaster")
                // 哨兵地址
                .sentinel("192.168.33.10", 26380)
                .sentinel("192.168.33.10", 26381)
                .sentinel("192.168.33.10", 26382);
        return new LettuceConnectionFactory(sentinelConfig);
    }
}
