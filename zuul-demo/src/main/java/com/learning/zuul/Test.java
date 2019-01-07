package com.learning.zuul;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: zhangll
 * @Date: 2019/1/5 16:47
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
        System.out.println("****************************newFixedThreadPool*******************************");
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(ZuulRequestContext.threadLocal.get());
            }
        };
        for(int i=0;i<10;i++)
        {
            newFixedThreadPool.execute(task);
        }
    }
}
