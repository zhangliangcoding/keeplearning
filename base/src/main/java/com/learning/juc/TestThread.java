package com.learning.juc;

/**
 * @Auther: zhangll
 * @Date: 2019/5/11 17:09
 * @Description:
 */
public class TestThread {


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            System.out.println("thread-1 start");
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread-1 end");
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            System.out.println("thread-2 start");
            t1.interrupt();
            System.out.println("thread-2 end");
        });
        Thread.sleep(3*1000);
        t2.start();

    }



}
