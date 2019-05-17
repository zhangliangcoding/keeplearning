package com.learning.classTest;

/**
 * @Auther: zhangll
 * @Date: 2019/5/16 09:45
 * @Description:
 */
public class Demo extends BaseClass {

    static {
        System.out.println("static area");
    }

    public Demo() {
        System.out.println("demo constructor");
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
    }
}
