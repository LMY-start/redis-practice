package com.self.learning.redis;

import com.self.learning.redis.dto.JdkPeople;
import com.self.learning.redis.dto.NormalPeople;
import com.self.learning.redis.dto.People;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RedisSerializeTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier(value = "genericToString")
    private RedisTemplate<String, People> genericToStringTemplate;

    @Autowired
    @Qualifier(value = "genericJacksonToJson")
    private RedisTemplate<String, Object> genericToJsonTemplate;

    @Autowired
    @Qualifier(value = "specificJacksonToJson")
    private RedisTemplate<String, NormalPeople> specificToJsonTemplate;

    @Autowired
    @Qualifier(value = "genericFastJsonToJson")
    private RedisTemplate<String, Object> genericFasJsonToJsonTemplate;

    @Test
    void should_save_string_success_use_string_serializer() {
        stringRedisTemplate.opsForValue().set("stringSerial", "hello");
        System.out.println(stringRedisTemplate.getKeySerializer().getClass().getName());
        System.out.println(stringRedisTemplate.getValueSerializer().getClass().getName());
        String stringSerial = stringRedisTemplate.opsForValue().get("stringSerial");
        assertEquals("hello", stringSerial);
    }

    @Test
    void should_save_string_success_use_jdk_serializer() {
        redisTemplate.opsForValue().set("jdk", "hello");
        System.out.println(redisTemplate.getKeySerializer().getClass().getName());
        System.out.println(redisTemplate.getValueSerializer().getClass().getName());
        String jdk = (String) redisTemplate.opsForValue().get("jdk");
        assertEquals("hello", jdk);
    }

    @Test
    void should_save_object_success_use_jdk_serializer() {
        JdkPeople people = new JdkPeople("KG", "male");
        testModule("jdkPeople", people, redisTemplate);
    }

    @Test
    void should_save_success_use_generic_2_string_serializer() {
        People people = new People("KG", "male");
        testModule("genericObjectToString", people, genericToStringTemplate);
    }

    @Test
    void should_save_success_use_generic_object_2_json_serializer() {
        NormalPeople normalPeople = new NormalPeople("ZY", "female");
        testModule("genericObjectToJson", normalPeople, genericToJsonTemplate);
    }

    @Test
    void should_save_success_use_object_2_json_serializer() {
        NormalPeople normalPeople = new NormalPeople("ZY", "female");
        testModule("specificToJson", normalPeople, specificToJsonTemplate);
    }

    @Test
    void should_save_success_use_fast_object_2_json_serializer() {
        NormalPeople normalPeople = new NormalPeople("ZY", "female");
        testModule("genericFastJson", normalPeople, genericFasJsonToJsonTemplate);
    }


    private void testModule(String key, Object value, RedisTemplate redisTemplate) {
        redisTemplate.opsForValue().set(key, value);
        System.out.println(redisTemplate.getKeySerializer().getClass().getName());
        System.out.println(redisTemplate.getValueSerializer().getClass().getName());
        Object result = redisTemplate.opsForValue().get(key);
        System.out.println(result);
    }
}
