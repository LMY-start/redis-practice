package com.self.learning.redis.configuration;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.self.learning.redis.dto.NormalPeople;
import com.self.learning.redis.dto.People;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfiguration {

    @Bean(name = "genericToString")
    RedisTemplate<String, People> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, People> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(People.class));
        return redisTemplate;
    }

    @Bean(name = "genericJacksonToJson")
    RedisTemplate<String, Object> redisGenericToJsonTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
        return redisTemplate;
    }

    @Bean(name = "specificJacksonToJson")
    RedisTemplate<String, NormalPeople> redisJacksonToJsonTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, NormalPeople> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(NormalPeople.class));
        return redisTemplate;
    }

    @Bean(name = "genericFastJsonToJson")
    RedisTemplate<String, Object> redisGenericFastJsonTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(new GenericFastJsonRedisSerializer());
        return redisTemplate;
    }
}
