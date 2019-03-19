package com.learning.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Auther: zhangll
 * @Date: 2019/1/7 11:25
 * @Description:
 */
@SpringBootApplication
@EnableZuulProxy
@EnableFeignClients(basePackages = {"com.learning.api"})
@EnableDiscoveryClient
@EnableAspectJAutoProxy
public class ZuulApp {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApp.class, args);
    }

}
