package io.daiwei.bytebuddy.forclazz;

import io.daiwei.bytebuddy.HelloTestMain;
import io.daiwei.rpc.demo.pojo.User;
import io.daiwei.rpc.demo.service.UserService;
import io.daiwei.rpc.demo.service.impl.UserServiceImpl;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.StubMethod;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Daiwei on 2021/4/14
 */
public class ClazzTestMain {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String clazzName = UserServiceImpl.class.getCanonicalName() + "$$daiweiRpxProxyByByteBuddy";
        UserServiceImpl instance = new ByteBuddy().subclass(UserServiceImpl.class).name(clazzName)
                .method(ElementMatchers.any()).intercept(MethodCall.invokeSuper().withAllArguments())
                .make().load(ClazzTestMain.class.getClassLoader()).getLoaded().newInstance();
//        Method[] methods = instance.getClass().getMethods();
        System.out.println(instance.getClass().getName());
//        System.out.println(String.join(",", Stream.of(methods).map(Method::getName).collect(Collectors.toList())));
        Method findById = instance.getClass().getMethod("crying", new Class[]{});
        System.out.println(findById.invoke(instance, new Object[]{}));
//        instance
    }
}
