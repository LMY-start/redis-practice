package com.self.learning.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JdkPeople implements Serializable {
    private static final long serialVersionUID = -938025684961584602L;
    private String name;
    private String gender;
}
