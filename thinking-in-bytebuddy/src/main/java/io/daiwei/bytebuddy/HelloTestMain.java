package io.daiwei.bytebuddy;

import io.daiwei.rpc.demo.pojo.User;
import io.daiwei.rpc.demo.service.UserService;
import io.daiwei.rpc.demo.service.impl.UserServiceImpl;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Daiwei on 2021/3/21
 */
public class HelloTestMain {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        UserService userService = new ByteBuddy().subclass(UserService.class)
                .method(ElementMatchers.any()).intercept(MethodDelegation.to(new DelegationClass()))
                .make().load(HelloTestMain.class.getClassLoader())
                .getLoaded().newInstance();
        User byId = userService.findById(1);
        System.out.println(byId);
    }
}
