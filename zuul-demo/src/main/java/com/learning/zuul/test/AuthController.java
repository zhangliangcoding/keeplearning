package com.learning.zuul.test;


import com.learning.zuul.domain.dto.ResponseDto;
import com.learning.zuul.req.LoginReq;
import com.learning.zuul.service.auth.AuthHandlerCollector;
import com.learning.zuul.service.auth.handler.LoginHandler;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: zhangll
 * @Date: 2019/1/12 10:44
 * @Description:
 */
@RestController
@RequestMapping()
public class AuthController {

    @Autowired
    private AuthHandlerCollector authHandlerCollector;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseDto login(LoginReq loginReq, HttpServletRequest request, HttpServletResponse response) {
        Assert.isTrue(loginReq.getLoginType() != null, "loginType不能为空");
        LoginHandler loginHandler = authHandlerCollector.getLoginHandler(loginReq.getLoginType());
        Assert.isTrue(loginHandler != null, "不支持的登陆方式");
        ResponseDto result = new ResponseDto();
        try {
            Object res = loginHandler.login(loginReq, request, response);
            result.setDate(res);
            result.setCode(1);
        } catch (Exception e) {
            result.setCode(-1);
            result.setMsg("登录失败");
            logger.error("登录失败");
        }
        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseDto logout(LoginReq loginReq, HttpServletRequest request, HttpServletResponse response) {
        Assert.isTrue(loginReq.getLoginType() != null, "loginType不能为空");
        LoginHandler loginHandler = authHandlerCollector.getLoginHandler(loginReq.getLoginType());
        Assert.isTrue(loginHandler != null, "不支持的登陆方式");
        ResponseDto result = new ResponseDto();
        try {
            Object res = loginHandler.logout(loginReq, request, response);
            result.setDate(res);
            result.setCode(1);
        } catch (Exception e) {
            result.setCode(-1);
            result.setMsg("登出失败");
            logger.error("登出失败");
        }
        return result;
    }

    @RequestMapping(value="/noPerm",method=RequestMethod.GET)
    public String noPerm(){
        return "noPerm";
    }

    @RequestMapping(value = "/notAllow", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDto notAllow(HttpServletRequest request, HttpServletResponse response) {

        ResponseDto responseDto = new ResponseDto();
        String from = (String) request.getAttribute("FROM");
        if ("DENY".equals(from)) {
            responseDto.setCode(-999);
            responseDto.setMsg("无访问权限或无此功能");
        }else if ("LOGIN_FAILED".equals(from)) {
            responseDto.setCode(-999);
            responseDto.setMsg("请重新登录");
        } else {
            responseDto.setMsg("诡异了，联系开发看日志吧");
        }
        return responseDto;
    }


}
