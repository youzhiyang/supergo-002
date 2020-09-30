package com.supergo.manager.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.supergo.manager.feign")  //到指定包下面去扫描feign接口
public class ManagerWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerWebApplication.class, args);
    }
}
