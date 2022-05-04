package io.daiwei.jvm.test;

import java.util.Arrays;

/**
 * Created by Daiwei on 2021/5/7
 */
public class TestMain {

    public static void main(String[] args) {
        HelloClass.sayHello();

        HiClass hiClass = new HiClass();
//        System.out.println(hiClass.hiYou("daiwei"));
//        GoodNight goodNight = (GoodNight) hiClass;
//        goodNight.goodNight("daiwei");
//        System.out.println(Arrays.stream(new int[]{1, 2, 3}).reduce(0, Integer::sum));
    }


}
