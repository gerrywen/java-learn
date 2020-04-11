package com.study.cache.redis.a7_cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * program: java-learn->ClusterService
 * description:
 * author: gerry
 * created: 2020-04-11 23:39
 **/
@Service
@Profile("a7_cluster")
public class ClusterService {
    @Autowired
    private StringRedisTemplate template;

    public void set(String userId, String userInfo) {
        template.opsForValue().set(userId, userInfo);
    }

    public String get(String userId) {
        return template.opsForValue().get(userId);
    }
}
