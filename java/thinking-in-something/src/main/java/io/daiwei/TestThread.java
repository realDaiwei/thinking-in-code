package io.daiwei;

import jdk.internal.org.objectweb.asm.Handle;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Daiwei on 2021/4/26
 */
public class TestThread {

    private final static Map<String, Integer> map  = new HashMap<>();

    public static void main(String[] args) {
        map.put("hello", 0);
        map.put("world", 0);

        new Thread(() -> {
            while (true) {
                Integer hello = map.get("hello");
                if (hello < 10) {
                    System.out.println("hello1 :" + hello);
                    map.put("hello", hello + 1);
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Integer hello = map.get("hello");
                if (hello < 10) {
                    System.out.println("hello2 :" + hello);
                    map.put("hello", hello + 1);

                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Integer hello = map.get("hello");
                if (hello < 10) {
                    System.out.println("hello3 :" + hello);
                    map.put("hello", hello + 1);
                }
            }
        }).start();
    }
}
