package com.learning.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhangll
 * @Date: 2018/12/8 17:47
 * @Description:
 */
public class Test {


    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 11);
        map.put(2, 22);
        map.put(3, 33);

        map.forEach((k, v) -> {
            System.out.println("k:" + k + "==>" + "v:" + v);
        });
        map.compute(1, (k, v) -> {
            return v + 100 + k;
        });
        System.out.println("1:"+map.get(1));

        map.compute(1, (k, v) -> {
            return null;
        });
        System.out.println("还有1吗？->" + map.containsKey(1));

        map.computeIfAbsent(2, (v) -> {
            return -2;
        });
        System.out.println("2:"+map.get(2));

        map.computeIfAbsent(4, (v) -> {
            return 444;
        });
        System.out.println("4:"+map.get(4));
        map.computeIfAbsent(4, (v) -> {
            return null;
        });
        System.out.println("4:"+map.get(4));
        System.out.println("还有4吗？->"+map.get(4));

        map.computeIfPresent(5, (k, v) -> {
            return 55;
        });
        System.out.println("5:"+map.get(5));
        System.out.println("有5吗?->"+map.containsKey(5));

        map.put(5, null);
        map.computeIfPresent(5, (k, v) -> {
            return 55;
        });
        System.out.println("5:"+map.get(5));
        System.out.println("有5吗?->"+map.containsKey(5));

        map.put(5, 5);
        map.computeIfPresent(5, (k, v) -> {
            return 55;
        });
        System.out.println("5:"+map.get(5));
        System.out.println("有5吗?->"+map.containsKey(5));

    }



}
