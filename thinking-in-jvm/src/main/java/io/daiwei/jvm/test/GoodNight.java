package io.daiwei.jvm.test;

/**
 * Created by Daiwei on 2021/5/7
 */
public interface GoodNight {

    default void goodNight(String name) {
        System.out.println("good night");
    }
}
