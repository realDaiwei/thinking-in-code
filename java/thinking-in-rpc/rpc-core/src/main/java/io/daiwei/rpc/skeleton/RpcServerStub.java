package io.daiwei.rpc.skeleton;

import io.daiwei.rpc.api.RpxFxResolver;
import io.daiwei.rpc.pojo.http.RpcFxRequest;
import io.daiwei.rpc.pojo.http.RpcFxResponse;
import io.daiwei.rpc.skeleton.resolve.ServiceMapRpcFxResolver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * 服务端存根
 * Created by Daiwei on 2021/3/19
 */
public class RpcServerStub {

    private RpxFxResolver resolver;


    public RpcServerStub() {
        this.resolver = new ServiceMapRpcFxResolver();
    }

    /**
     * rpc provider 找到实际提供的服务并调用
     * @param request
     * @return
     */
    public RpcFxResponse invoke(final RpcFxRequest request) {
        RpcFxResponse resp = null;
        try {
            Object resolve = resolver.resolve(request.getServiceClass());
            Method method = findMethodFromClazz(resolve.getClass(), request.getMethod());
            Object res = method.invoke(resolve, request.getArgs());
            resp =  RpcFxResponse.builder().code(0).exception(null).data(res).build();
        } catch (IllegalAccessException | InvocationTargetException e) {
            resp = RpcFxResponse.builder().code(-1).exception(e).build();
        }
        return resp;
    }

    /**
     * 注册服务
     * @param obj
     */
    public void register(Object obj) {
        for (Class<?> clazz : obj.getClass().getInterfaces()) {
            resolver.register(clazz.getName(), obj);
        }
    }

    private Method findMethodFromClazz(Class<?> clazz, String method) {
        return Arrays.stream(clazz.getMethods()).filter(m -> m.getName().equals(method)).findFirst().orElse(null);
    }

}
