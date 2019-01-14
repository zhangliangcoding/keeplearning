package com.learning.zuul.service.auth.handler;


import com.learning.zuul.req.LoginReq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: zhangll
 * @Date: 2019/1/14 10:57
 * @Description:
 */
public abstract class LoginHandler {

    /**
     * 登陆方式
     * @return
     */
    public abstract Integer getLoginType();

    public abstract Object login(LoginReq loginReq, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public abstract Object logout(LoginReq loginReq, HttpServletRequest request, HttpServletResponse response) throws Exception;


}
