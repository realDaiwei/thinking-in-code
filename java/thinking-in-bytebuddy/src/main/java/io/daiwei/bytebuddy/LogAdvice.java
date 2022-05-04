package io.daiwei.bytebuddy;

import net.bytebuddy.asm.Advice;

import java.util.Arrays;

/**
 * Created by Daiwei on 2021/3/22
 */
public class LogAdvice {

    @Advice.OnMethodEnter
    public static void adviceIn(@Advice.This Object targetObj, @Advice.AllArguments Object[] allArgs) {
        System.out.println(targetObj.getClass().getName());
        System.out.println(Arrays.toString(allArgs));
        System.out.println("method in ~");
    }


    @Advice.OnMethodExit
    public static void adviceOut() {
        System.out.println("method out~");
    }
}
