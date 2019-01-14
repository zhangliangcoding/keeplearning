package com.learning.zuul.service.auth.handler;


import com.learning.zuul.req.LoginReq;
import com.learning.zuul.security.util.JWTUtil;
import com.learning.zuul.util.LoginTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhangll
 * @Date: 2019/1/14 10:56
 * @Description:
 */
@Service
public class QiyeWXLoginHandler extends LoginHandler{

    @Autowired
    JWTUtil jwtUtil;

    @Override
    public Integer getLoginType() {
        return LoginTypeEnum.QYWXCODE.getValue();
    }

    @Override
    public Object login(LoginReq loginReq, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String jwt = jwtUtil.generateToken(loginReq.getAccount());
        Map<String, Object> respond = new HashMap<>();
        respond.put("jwt", jwt);
        response.setHeader("Authorization", jwt);
        return respond;
    }

    @Override
    public Object logout(LoginReq loginReq, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }
}
