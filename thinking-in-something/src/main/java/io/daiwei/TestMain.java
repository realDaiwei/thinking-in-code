package io.daiwei;

/**
 * Created by Daiwei on 2021/4/28
 */
public class TestMain {

    public static void main(String[] args) {
        HelloTest helloTest = new HelloTest();
        helloTest.setHello(true);
        helloTest.add("hello");
        Hello hello = new Hello(helloTest.getHello(), helloTest.getSet(), helloTest.getCnt());
//        hello.size();
        helloTest.add();

        helloTest.add();
        helloTest.add("world");
        helloTest.setHello(false);
//        hello.size();

        hello.printCnt();
        hello.add();

        helloTest.printCnt();


    }
}
