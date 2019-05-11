package com.learning.juc;

/**
 * @Auther: zhangll
 * @Date: 2019/5/11 17:33
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        Thread th = Thread.currentThread();
        while (true) {
            if (th.isInterrupted()) {
                break;
            }
            // 省略业务代码无数
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}