package io.daiwei.rpc.stub.handler;

import com.alibaba.fastjson.JSON;
import io.daiwei.rpc.pojo.http.RpcFxRequest;
import io.daiwei.rpc.pojo.http.RpcFxResponse;
import lombok.Getter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 基于 cglib 的 rpc invoker
 * Created by Daiwei on 2021/3/19
 */
public class RpcInvokeHttpHandler implements MethodInterceptor {

    private final Enhancer enhancer;

    private final String url;

    private Class<?> proxyClazz;

    /**
     * 这里可以单独为http创建一个 rpc http 的连接池
     */
    private final OkHttpClient client;

    private final MediaType mediaType = MediaType.get("application/json; charset=utf-8");

    public RpcInvokeHttpHandler() {
        this.enhancer = new Enhancer();
        this.client = new OkHttpClient();
        this.url = "http://127.0.0.1:8080/";
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        RpcFxRequest request = RpcFxRequest.builder().serviceClass(this.proxyClazz.getName()).method(method.getName())
                .args(objects).build();
        RpcFxResponse response = post(request, this.url);
        return JSON.parseObject(response.getData().toString(), method.getReturnType());
    }

    public <T> T create(Class<T> clazz) {
        this.proxyClazz = clazz;
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return clazz.cast(enhancer.create());
    }

    private RpcFxResponse post(final RpcFxRequest req, String url) throws IOException {
        String reqJson = JSON.toJSONString(req);
        final Request request = new Request.Builder()
                .url(url).post(RequestBody.create(reqJson, mediaType))
                .build();
        String bodyStr = client.newCall(request).execute().body().string();
        return JSON.parseObject(bodyStr, RpcFxResponse.class);
    }


}
