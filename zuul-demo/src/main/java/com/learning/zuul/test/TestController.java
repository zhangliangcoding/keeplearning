package com.learning.zuul.test;

import com.learning.api.TestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zhangll
 * @Date: 2019/1/9 19:28
 * @Description:
 */
@RestController
public class TestController {

    @Autowired
    TestApi testApi;

    @GetMapping(value = "/hello")
    public void hello(){
        testApi.test();
    }
}
