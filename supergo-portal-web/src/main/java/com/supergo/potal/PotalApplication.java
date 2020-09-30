package com.supergo.potal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * PotalApplication起步依赖  门户网站
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.supergo.manager.feign")    //调用feign接口
public class PotalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PotalApplication.class, args);
    }
}
