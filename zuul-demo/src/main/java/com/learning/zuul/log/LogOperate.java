package com.learning.zuul.log;

import java.lang.annotation.*;

/**
 * Created by Mocha on 2019/1/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface LogOperate {

    String desc();

}
