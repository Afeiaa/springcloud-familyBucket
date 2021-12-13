package com.afei.redis.opsString;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;

/**
 *  RedisTemplate  StringRedisTemplate
 *  以下方法都是针对String, opsForValue()
 */
@RestController
public class StringTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/test1")
    public String test1() {
        redisTemplate.opsForValue().set("k1", 1);
        redisTemplate.opsForValue().increment("k1", 1);
        System.out.println(redisTemplate.opsForValue().get("k1"));

        redisTemplate.delete("k1");

        Boolean k1 = redisTemplate.hasKey("k1");
        System.out.println(k1);

        return "success！";
    }

}
