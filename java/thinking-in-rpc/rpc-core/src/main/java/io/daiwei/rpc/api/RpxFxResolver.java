package io.daiwei.rpc.api;

/**
 * resolve 获取查找目标 bean
 * Created by Daiwei on 2021/3/19
 */
public interface RpxFxResolver {

    Object resolve(String clazzName);

    void register(String name, Object service);
}
