package io.daiwei.rpc.stub;

import com.alibaba.fastjson.parser.ParserConfig;
import io.daiwei.rpc.stub.handler.RpcInvokeHttpHandler;

import javax.xml.parsers.ParserConfigurationException;

/**
 * 客户端存根
 * Created by Daiwei on 2021/3/19
 */
public class RpcClientStubFactory {

    static {
        ParserConfig.getGlobalInstance().addAccept("io.daiwei");
    }

    private RpcInvokeHttpHandler rpcInvokeHandler;

    public RpcClientStubFactory() {
        this.rpcInvokeHandler = new RpcInvokeHttpHandler();
    }

    public <T> T createStub(Class<T> clazz) {
        return this.rpcInvokeHandler.create(clazz);
    }



}
