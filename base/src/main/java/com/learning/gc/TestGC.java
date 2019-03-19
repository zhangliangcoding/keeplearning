package com.learning.gc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Mocha on 2019/3/6.
 */
public class TestGC {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/test");
        String domain = url.getHost();
        System.out.println(domain);

        AtomicInteger i = new AtomicInteger(1);
        System.out.println(i.incrementAndGet());
        System.out.println(i.getAndIncrement());

        int ii = 1;
        System.out.println(ii++);
        int jj = 1;
        System.out.println(++jj);


    }


}
