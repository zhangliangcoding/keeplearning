package com.learning.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: zhangll
 * @Date: 2019/1/7 14:22
 * @Description:
 */
//@SpringBootApplication
//@EnableEurekaClient
public class TestApp {

    public static void main(String[] args) {
//        SpringApplication.run(TestApp.class, args);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(5);
        list1.add(4);
        list.removeAll(list1);
        System.out.println(list);
    }

}
