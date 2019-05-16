package com.learning.classTest;

/**
 * @Auther: zhangll
 * @Date: 2019/5/16 09:45
 * @Description:
 */
public class BaseClass {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private void testPrivate(){
        System.out.println("base class test private ");
    }

}
