package com.learning;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: zhangll
 * @Date: 2019/3/15 14:22
 * @Description:
 */
@SpringBootApplication
public class NettyServerApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NettyServerApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
