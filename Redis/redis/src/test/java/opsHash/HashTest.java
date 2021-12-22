package opsHash;

import com.afei.redis.RedisApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = RedisApplication.class)
public class HashTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void test1() {
        String hKey = "testHash";
        // 1. 增 map
        Map<String, String> hvMap = new HashMap<>();
        hvMap.put("1", "1");
        hvMap.put("2", "2");
        hvMap.put("3", "3");
        redisTemplate.opsForHash().putAll(hKey ,hvMap);

        // 2. 增 单个
        redisTemplate.opsForHash().put(hKey, "4", "4");

        System.out.println("-----------------add----------------");
        // 3. 遍历
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(hKey);
        entries.entrySet().stream().forEach(item -> {
            System.out.println(item.getKey() + ":"
                    + item.getValue());
        });

        System.out.println("-----------------delete----------------");
        // 4. 删除单个
        redisTemplate.opsForHash().delete(hKey, "4");
        entries = redisTemplate.opsForHash().entries(hKey);
        entries.entrySet().stream().forEach(item -> {
            System.out.println(item.getKey() + ":"
                    + item.getValue());
        });

        // 5. 获取
        System.out.println("-----------------get----------------");
        System.out.println(redisTemplate.opsForHash().get(hKey, "1"));

        // 6. length
        System.out.println("-----------------length----------------");
        Long aLong = redisTemplate.opsForHash().lengthOfValue(hKey, "1");
        System.out.println(aLong);

        // 7. size
        System.out.println("-----------------size----------------");
        Long size = redisTemplate.opsForHash().size(hKey);
        System.out.println(size);

        // 8. 是否存在
        System.out.println("-----------------hasKey----------------");
        Boolean aBoolean = redisTemplate.opsForHash().hasKey(hKey, "5");
        System.out.println(aBoolean);

        // 9. 删除key
        redisTemplate.delete(hKey);

    }
}
