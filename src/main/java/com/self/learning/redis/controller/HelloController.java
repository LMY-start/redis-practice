package com.self.learning.redis.controller;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@CacheConfig(cacheNames = {"hello"})
public class HelloController {

    private static final String NICE_TO_MEET_YOU = "nice to meet you";
    private static final String NICE_TO_MEET_YOU_TOO = "nice to meet you, too";

    @Cacheable(key = "#name")
    @GetMapping
    public String sayHello(@RequestParam String name) {
        System.out.println("not use cache in read");
        return NICE_TO_MEET_YOU + ", " + name;
    }

    @CachePut(key = "#name")
    @PostMapping
    public String receiveHello(@RequestParam String name) {
        System.out.println("always run to write cache");
        return NICE_TO_MEET_YOU_TOO + ", " + name;
    }

}
