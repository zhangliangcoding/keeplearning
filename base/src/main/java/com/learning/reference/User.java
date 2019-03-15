package com.learning.reference;

/**
 * @Auther: zhangll
 * @Date: 2019/3/4 18:00
 * @Description:
 */
public class User {

    private Integer userId;
    private String name;

    public User(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
