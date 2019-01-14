package com.learning.zuul.service.auth;

import com.learning.zuul.service.auth.handler.LoginHandler;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhangll
 * @Date: 2019/1/14 10:55
 * @Description:
 */
@Component
public class AuthHandlerCollector implements ApplicationContextAware,InitializingBean {

    final Map<Integer, LoginHandler> loginHandlerCacheMap = new HashMap<>();
    ApplicationContext applicationContext;


    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, LoginHandler> map = applicationContext.getBeansOfType(LoginHandler.class);
        for (LoginHandler handler : map.values()) {
            Integer loginType = handler.getLoginType();
            Assert.isTrue(!loginHandlerCacheMap.containsKey(loginType), String.format("find two same type=%s LoginHandler", loginType + ""));
            loginHandlerCacheMap.put(loginType, handler);
        }
    }

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public LoginHandler getLoginHandler(Integer type) {
        return loginHandlerCacheMap.get(type);
    }
}
