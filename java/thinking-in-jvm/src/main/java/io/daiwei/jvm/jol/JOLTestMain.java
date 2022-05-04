package io.daiwei.jvm.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * Created by Daiwei on 2021/5/14
 */
public class JOLTestMain {

    public static void main(String[] args) {
        B b = new B();
        System.out.println(b.hashCode());
//        A a = new A();
//        int[] nums = new int[10];
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
    }
}
