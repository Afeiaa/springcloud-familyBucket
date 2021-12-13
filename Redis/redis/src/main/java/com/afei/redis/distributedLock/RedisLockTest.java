package com.afei.redis.distributedLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisLockTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/testRedisLock")
    public String redisLock() {
        String lockValue = UUID.randomUUID().toString();

        try {
            // 加唯一id锁
            boolean flag = stringRedisTemplate.opsForValue().setIfAbsent("lockKey", lockValue, 15, TimeUnit.SECONDS);
            if (!flag) {
                return "error";
            }

            Integer stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                String num = String.valueOf(stock -1);
                stringRedisTemplate.opsForValue().set("stock", num);
                System.out.println("扣减库存成功，剩余库存：" + num);
            } else {
                System.out.println("扣减库存失败！");
            }
        } finally {
            if (lockValue.equals(stringRedisTemplate.opsForValue().get("lockKey"))) {
                stringRedisTemplate.delete("lockKey");
            }
        }

        return "success";
    }

}
