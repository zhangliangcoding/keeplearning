package com.learning.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: zhangll
 * @Date: 2019/5/11 14:29
 * @Description:
 */
public class MyLock {

    public static void main(String[] args) throws InterruptedException {

        Account a = new Account(100000);
        Account b = new Account(100000);
        CountDownLatch countDown = new CountDownLatch(100000);

        for (int i = 0; i < 100000; i++) {
            new Thread(()->{
                a.transcationToTarget(1, b);
                countDown.countDown();
            }).start();
        }
        countDown.await();
        System.out.println("========================");
        System.out.println("a balance :" + a.balance);
        System.out.println("b balance :" + b.balance);


    }






    static class Account{

        private Integer balance;

        public Account(Integer balance) {
            this.balance = balance;
        }

        public void transcationToTarget(Integer money, Account target) {
            Allocator.getInstance().apply(this, target);
            this.balance = balance - money;
            target.balance = target.balance + money;
            Allocator.getInstance().release(this, target);
        }

        public Integer getBalance() {
            return balance;
        }

        public void setBalance(Integer balance) {
            this.balance = balance;
        }



    }

    static class Allocator{

        private List<Account> locks = new ArrayList<>();

        private Allocator() {
        }

        public static Allocator getInstance(){
            return AllocatorSingle.intance;
        }

        static class AllocatorSingle{
            public static Allocator intance = new Allocator();
        }

        synchronized void apply(Account a, Account b) {
            while (locks.contains(a) ||
                    locks.contains(b)) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }

            locks.add(a);
            locks.add(b);
        }

        synchronized void release(Account a, Account b) {
            locks.remove(a);
            locks.remove(b);
            this.notifyAll();
        }

    }

}
