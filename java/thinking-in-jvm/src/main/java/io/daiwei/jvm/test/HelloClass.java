package io.daiwei.jvm.test;

/**
 * Created by Daiwei on 2021/5/7
 */
public class HelloClass {

    {
        System.out.println("init block");
    }

    public HelloClass() {
        System.out.println("init!");
    }

    public static void sayHello() {
        System.out.println("hello");
    }

    public void hello() {
        System.out.println("hello from helloClass");
    }

    public String hiYou(String you) {
        return "good night " + you + "!!";
    }
}
