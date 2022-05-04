package io.daiwei.rpc.skeleton.resolve;

import io.daiwei.rpc.api.RpxFxResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用 service Map 的方式做服务的映射
 * Created by Daiwei on 2021/3/20
 */
public class ServiceMapRpcFxResolver implements RpxFxResolver {

    private final Map<String, Object> serviceMap;

    public ServiceMapRpcFxResolver() {
        this.serviceMap = new HashMap<>();
    }

    @Override
    public Object resolve(String clazzName) {
        return serviceMap.get(clazzName);
    }

    @Override
    public void register(String name, Object service) {
        serviceMap.put(name, service);
    }
}
