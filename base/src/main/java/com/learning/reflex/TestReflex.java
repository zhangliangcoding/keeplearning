package com.learning.reflex;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auther: zhangll
 * @Date: 2019/5/16 10:05
 * @Description:
 */
public class TestReflex {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Son son = new Son();
        Class clazz = son.getClass();
        System.out.println(clazz.getName());

        Class parentClazz = clazz.getSuperclass();
        Object parentClazzInstance = parentClazz.newInstance();
        System.out.println(parentClazz.getName());

        Method[] sonMethods = clazz.getDeclaredMethods();
        for (int i = 0; i < sonMethods.length; i++) {
            System.out.println("sonMethods : " + sonMethods[i].getName());
        }

        Method[] parentMethods = parentClazz.getDeclaredMethods();
        for (int i = 0; i < parentMethods.length; i++) {
            System.out.println("parentMethods : " + parentMethods[i].getName());
            if ("testPrivate".equals(parentMethods[i].getName())) {
                try {
                    parentMethods[i].setAccessible(true);
                    parentMethods[i].invoke(new Object());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

    }




}
