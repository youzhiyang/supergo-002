package com.supergo;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.supergo.mapper")
public class MapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapperApplication.class);
    }
}
