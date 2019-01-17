package com.learning.container;

import org.eclipse.jetty.client.HttpClient;

/**
 * @Auther: zhangll
 * @Date: 2019/1/16 16:13
 * @Description:
 */
public class MyContainer extends HttpClient {

    public static void main(String[] args) throws Exception {
        MyContainer myContainer = new MyContainer();
        myContainer.start();
    }

}
