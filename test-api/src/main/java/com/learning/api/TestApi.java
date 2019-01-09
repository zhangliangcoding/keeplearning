package com.learning.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: zhangll
 * @Date: 2019/1/8 15:30
 * @Description:
 */
@FeignClient("test-app")
public interface TestApi {

    @GetMapping("/test")
    void test();
}
