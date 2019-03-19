package com.learning.test;

import com.learning.zuul.ZuulApp;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Mocha on 2019/1/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZuulApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {
}
