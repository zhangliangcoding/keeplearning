package com.learning.map.test;

import com.learning.api.TestApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zhangll
 * @Date: 2019/1/7 14:51
 * @Description:
 */
@RestController
public class TestController implements TestApi {
    @GetMapping(value = "/test")
    public void test(){
        System.out.println("=== > test");
    }
}
