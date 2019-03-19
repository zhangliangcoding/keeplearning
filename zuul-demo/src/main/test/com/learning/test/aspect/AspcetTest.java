package com.learning.test.aspect;

import com.learning.test.BaseTest;
import com.learning.zuul.domain.entity.User;
import com.learning.zuul.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Mocha on 2019/1/15.
 */
public class AspcetTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void testAspect(){
        User user = userService.findByUsername("mocha");
        System.out.println(user.getUsername());
    }

}
