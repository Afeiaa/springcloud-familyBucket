package com.afei.redis.opsHash;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HashTest {                     // <H, HK, HV>

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/test2")
    public String test2() {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("k1", 1);
        map1.put("k2", 2);
        map1.put("k3", 3);
        redisTemplate.opsForHash().putAll("map1", map1);
        redisTemplate.opsForHash().delete("map1", "k1");    // 删除H中的 HK=“k1” , HK可以是多个值
        redisTemplate.opsForHash().put("map1", "k1", "v1");  // 往H中放入, <HK, HV>
        Map<Object, Object> map1Redis = redisTemplate.opsForHash().entries("map1");
        for (Map.Entry<Object, Object> mapFromRedis : map1Redis.entrySet()) {
            System.out.println((String) mapFromRedis.getKey() + ": " + String.valueOf(mapFromRedis.getValue()));
        }

        System.out.println(redisTemplate.opsForHash().get("map1", "k2"));   // 根据HK获得H中的HV
        redisTemplate.opsForHash().put("map1", "k4", 3);  // 往H中放入, <HK, HV>

        System.out.println(redisTemplate.opsForHash().increment("map1", "k4", 2));

        System.out.println(redisTemplate.opsForHash().increment("map1", "k4", -3));

        System.out.println(redisTemplate.opsForHash().hasKey("map1", "k5"));

        System.out.println(redisTemplate.opsForHash().lengthOfValue("map1", "k1"));

        return "success!";
    }

}
