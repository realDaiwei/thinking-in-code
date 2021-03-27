package io.daiwei.bytebuddy;

import io.daiwei.rpc.demo.pojo.User;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.bind.annotation.*;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Daiwei on 2021/3/21
 */
public class DelegationClass {

    @RuntimeType
    public Object intercept(@This Object target, @AllArguments Object[] args, @Origin Method method, @Super Object clazz) {
        System.out.println("do something");
        Class<?>[] interfaces = target.getClass().getInterfaces();
        System.out.println(clazz.getClass());
        System.out.println(target.getClass().getName());
        System.out.println(Arrays.toString(args));
        System.out.println(method.getReturnType().getName());
        return User.builder().userName("daiwei666").age(26).id(2).build();
    }

}
