package io.daiwei.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by Daiwei on 2021/10/7
 */
public class UnsafeTest {

    public static void main(String[] args) throws Exception {
//        这样并不能直接拿到 unsafe，因为unsafe中必须是bootstrap classLoad 才能拿到。
//        Unsafe unsafe = Unsafe.getUnsafe();

        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);

        A a = new A();
        a.printA();

        A a1 = A.class.newInstance();
        a1.printA();

        A a2 = (A) unsafe.allocateInstance(A.class);
        a2.printA();
    }

    static class A {

        private long a;

        public A() {
            this.a = 666L;
        }

        public void printA() {
            System.out.println(a);
        }
    }
}
