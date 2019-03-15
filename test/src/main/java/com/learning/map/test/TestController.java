package com.learning.map.test;

import com.learning.api.TestApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Auther: zhangll
 * @Date: 2019/1/7 14:51
 * @Description:
 */
@RestController
public class TestController /*implements TestApi*/ {
    @GetMapping(value = "/login-center/welcome")
    public void test(HttpServletResponse response, HttpServletRequest request) throws MalformedURLException {
        System.out.println(request.getContextPath());
        System.out.println(request.getRequestURI());
        System.out.println(request.getScheme());
        System.out.println(request.getRequestURL());
        URL url = new URL(request.getRequestURL().toString());
        System.out.println(url.getHost());
        Cookie cookie = new Cookie("T", "test");
        response.addCookie(cookie);
        System.out.println("=== > test");
    }
}
