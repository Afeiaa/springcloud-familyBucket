package com.afei.redis.distributedLock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class RedissonLockTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/testRedissonLock")
    public String redisLock() {
        RLock redissonLock = redissonClient.getLock("lockKey");    // 锁 + 分线程监听

        try {
            // 加唯一id锁
            redissonLock.lock(15, TimeUnit.SECONDS);

            Integer stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                String num = String.valueOf(stock -1);
                stringRedisTemplate.opsForValue().set("stock", num);
                System.out.println("扣减库存成功，剩余库存：" + num);
            } else {
                System.out.println("扣减库存失败！");
            }
        } finally {
            redissonLock.unlock();
        }

        return "success";
    }
}
