package com.self.learning.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class People  {
    private String name;
    private String gender;


    public static People from(String name) {
        return new People(name, "unknown");
    }

}
