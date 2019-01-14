package com.learning.zuul.req;

/**
 * @Auther: zhangll
 * @Date: 2019/1/12 16:56
 * @Description:
 */
public class LoginReq {

    private Integer loginType;

    private String account;

    private String password;


    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
