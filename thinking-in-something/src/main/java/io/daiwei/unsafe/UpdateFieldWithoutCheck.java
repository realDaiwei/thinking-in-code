package io.daiwei.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by Daiwei on 2021/10/7
 */
public class UpdateFieldWithoutCheck {

    public static void main(String[] args) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);

        SecretKeeper secretKeeper = new SecretKeeper();

        Field secret = secretKeeper.getClass().getDeclaredField("SECRET");
        unsafe.putInt(secretKeeper, unsafe.objectFieldOffset(secret), 12581);

        secretKeeper.tellMeYourSecret();

    }

    static class SecretKeeper {

        // 当该成员变量被final修饰时，无法被修改
        // 当成员变量被 static修饰时，强制使用unsafe修改会报错。
        // 当成员变量没有被final和static修饰时，unsafe可以成功修改。
        private int SECRET = 10086;

        public void tellMeYourSecret() {
            System.out.println(SECRET);
        }
    }
}
