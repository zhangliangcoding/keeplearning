package com.learning.hystrix.my;

import com.learning.hystrix.UserAccount;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @Auther: zhangll
 * @Date: 2019/1/23 11:46
 * @Description:
 */
public class MyCommand extends HystrixCommand<UserAccount> {

    protected MyCommand() {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(UserAccount.class.getSimpleName())));
    }

    @Override
    protected UserAccount run() throws Exception {

        try {
            Thread.sleep((int) (Math.random() * 10) + 2);
        } catch (InterruptedException e) {
            // do nothing
        }

        /* fail 5% of the time to show how fallback works */
        if (Math.random() > 0.95) {
            throw new RuntimeException("random failure processing UserAccount network response");
        }

        /* latency spike 5% of the time so timeouts can be triggered occasionally */
        if (Math.random() > 0.95) {
            // random latency spike
            try {
                Thread.sleep((int) (Math.random() * 300) + 25);
            } catch (InterruptedException e) {
                // do nothing
            }
        }

        return new UserAccount(86975, "leoz", 2, true, false, true);
    }
}
