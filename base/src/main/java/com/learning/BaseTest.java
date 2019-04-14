package com.learning;

/**
 * Created by Mocha on 2019/4/14.
 */
public class BaseTest {

    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
    }

}
