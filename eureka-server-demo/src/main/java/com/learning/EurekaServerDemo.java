package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Auther: zhangll
 * @Date: 2019/1/8 10:43
 * @Description:
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerDemo {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerDemo.class, args);
    }

}
