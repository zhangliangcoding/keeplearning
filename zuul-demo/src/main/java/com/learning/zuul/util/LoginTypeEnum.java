package com.learning.zuul.util;

/**
 * @Auther: zhangll
 * @Date: 2019/1/12 16:59
 * @Description:
 */
public enum LoginTypeEnum {
    QYWXCODE(0),
    WXCODE(1),
    PHONE(2),
    EMAIL(3);

    private Integer value;

    LoginTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
