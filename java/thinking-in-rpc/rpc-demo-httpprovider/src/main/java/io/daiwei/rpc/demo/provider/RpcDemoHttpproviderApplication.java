package io.daiwei.rpc.demo.provider;

import io.daiwei.rpc.demo.pojo.User;
import io.daiwei.rpc.demo.service.impl.UserServiceImpl;
import io.daiwei.rpc.pojo.http.RpcFxRequest;
import io.daiwei.rpc.pojo.http.RpcFxResponse;
import io.daiwei.rpc.skeleton.RpcServerStub;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@SpringBootApplication
public class RpcDemoHttpproviderApplication {

    @Resource
    private RpcServerStub stub;

    public static void main(String[] args) {
        SpringApplication.run(RpcDemoHttpproviderApplication.class, args);
    }

    @Bean
    public RpcServerStub stub() {
        RpcServerStub rpcServerStub = new RpcServerStub();
        rpcServerStub.register(new UserServiceImpl());
        return rpcServerStub;
    }


    @PostMapping("/")
    public RpcFxResponse invoke(@RequestBody RpcFxRequest request) {
        System.out.println("invoke ~" + request.toString());
        return stub.invoke(request);
    }



}
