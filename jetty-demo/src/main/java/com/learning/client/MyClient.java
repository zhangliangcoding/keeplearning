package com.learning.client;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.util.ssl.SslContextFactory;

/**
 * @Auther: zhangll
 * @Date: 2019/1/16 15:39
 * @Description:
 */
public class MyClient {

    public static void main(String[] args) throws Exception {

        SslContextFactory sslContextFactory = new SslContextFactory();


        HttpClient httpClient = new HttpClient(sslContextFactory);
        httpClient.start();
//        ContentResponse response = httpClient.GET("http://data.eastmoney.com/hsgt/top10/2019-01-15.html");
//
//        System.out.println(response.getContentAsString());
    }

}
