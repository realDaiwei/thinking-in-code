package io.daiwei.jvm.test;

import java.lang.reflect.Method;

/**
 * Created by Daiwei on 2021/5/9
 */
public class TestReflectMain {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Class klass = Class.forName("io.daiwei.jvm.test.TestReflectMain");
            Method method = klass.getMethod("reflectException", int.class);
            method.invoke(null, i);
        }
    }

    public static void reflectException(int i) {
        new RuntimeException("#" + i).printStackTrace();
    }
}
