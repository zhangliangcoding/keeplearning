package com.learning.reflex;

/**
 * @Auther: zhangll
 * @Date: 2019/5/16 10:07
 * @Description:
 */
public class Parent {

    private String name;

    protected Integer age;

    public String tel;

    String address;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    protected void testProtected(){
        System.out.println("test protected");
    }
    private void testPrivate(){
        System.out.println("test private");
    }
    void testDefault(){
        System.out.println("test default");
    }

    public void testPublic(){
        System.out.println("test default");
    }

}
