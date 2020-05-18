package com.self.learning.redis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private static final String NICE_TO_MEET_YOU = "nice to meet you";

    @GetMapping
    public String sayHello() {
        return NICE_TO_MEET_YOU;
    }

}
