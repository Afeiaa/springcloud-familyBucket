package com.afei.redis.opsHash;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HashTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/test2")
    public String test2() {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("k1", 1);
        map1.put("k2", 2);
        map1.put("k3", 3);
        redisTemplate.opsForHash().putAll("map1", map1);
        Map<Object, Object> map1Redis = redisTemplate.opsForHash().entries("map1");
        for (Map.Entry<Object, Object> mapFromRedis : map1Redis.entrySet()) {
            System.out.println((String) mapFromRedis.getKey() + ": " + (Integer) mapFromRedis.getValue());
        }

        return "success!";
    }

}
