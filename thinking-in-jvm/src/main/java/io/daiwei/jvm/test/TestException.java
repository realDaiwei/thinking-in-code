package io.daiwei.jvm.test;

/**
 * Created by Daiwei on 2021/5/9
 */
public class TestException {

    public static void main(String[] args) {
        try {
            throw new RuntimeException("test");
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally!");
        }
    }
}
