package com.learning.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhangll
 * @Date: 2018/12/8 17:47
 * @Description:
 */
public class Test {


    public static void main(String[] args) {

        int n = 25;
//        System.out.println(Integer.toBinaryString(n));
//        System.out.println(n >>> 1);
//        System.out.println(Integer.toBinaryString(n >>> 1));
        n |= n >>> 1;
        System.out.println(n);
//        System.out.println(Integer.toBinaryString(n ));
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n);
        n |= n >>> 16;
        System.out.println(n);

    }



}
