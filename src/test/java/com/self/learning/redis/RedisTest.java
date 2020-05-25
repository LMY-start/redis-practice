//package com.self.learning.redis;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cache.CacheManager;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//import java.util.stream.IntStream;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class RedisTest {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    @Autowired
//    private CacheManager cacheManager;
//
//    @Test
//    void should_set_value_success() {
//        stringRedisTemplate.opsForValue().set("KG", "hello");
//    }
//
//
//    @Test
//    void should_execute_pipeline_success() {
//        List<Object> objects = stringRedisTemplate.executePipelined((RedisCallback<String>) connection -> {
//            IntStream.range(0, 3).forEach(index -> connection.set(String.valueOf(index).getBytes(), String.valueOf('A' + index).getBytes()));
//            IntStream.range(0, 3).forEach(index -> connection.get(String.valueOf(index).getBytes()));
//            return null;
//        });
//        System.out.println(objects);
//    }
//
//    @Test
//    void should_return_current_cache_type() {
//        System.out.println(cacheManager);
//    }
//}
