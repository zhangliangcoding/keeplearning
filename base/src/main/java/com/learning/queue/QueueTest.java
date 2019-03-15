package com.learning.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Auther: zhangll
 * @Date: 2019/3/11 15:25
 * @Description:
 */
public class QueueTest {

    /**
     * add
     * put
     * offer
     *
     * peek
     * take
     * pool
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);

        queue.add(1);
        System.out.println("当前queue有一个元素"+queue.peek());

        Thread get = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("get peek "+queue.peek());
                System.out.println("get poll "+queue.poll());//poll 不阻塞 如果没有元素 直接返回null
                System.out.println("get peek "+queue.peek());
                try {
                    System.out.println("get take " + queue.take());//take方法阻塞，只到有元素后返回元素
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread put = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queue.put(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("put size "+queue.size());
                System.out.println("put peek "+queue.peek());
                System.out.println("put poll "+queue.poll());

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                queue.offer(3);

            }
        });
        put.start();
        get.start();


    }

}
