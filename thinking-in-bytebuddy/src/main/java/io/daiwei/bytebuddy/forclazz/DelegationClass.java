package io.daiwei.bytebuddy.forclazz;

import io.daiwei.rpc.demo.pojo.User;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * Created by Daiwei on 2021/3/21
 */
public class DelegationClass {

    @RuntimeType
    public Object intercept(@SuperCall Callable<?> call) throws Exception {
        return call.call();
    }

}
