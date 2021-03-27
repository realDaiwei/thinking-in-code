package io.daiwei.bytebuddy;

import io.daiwei.rpc.demo.pojo.User;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

import java.util.Arrays;

/**
 * Created by Daiwei on 2021/3/22
 */
public class BeforeAdvice {

    @RuntimeType
    public Object beforeAdvice(@This Object target, @AllArguments Object[] args) {
        System.out.println("before advice");
        System.out.println(target.getClass().getName());
        System.out.println(Arrays.toString(args));
        return null;
    }
}
