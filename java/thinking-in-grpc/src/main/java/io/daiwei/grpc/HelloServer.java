package io.daiwei.grpc;

import io.daiwei.grpc.service.AddService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * Created by Daiwei on 2021/3/16
 */
public class HelloServer {

    private static final int port = 7777;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(port).addService(new AddService())
                .build().start();
        System.out.println("服务启动监听: " + port);
        server.awaitTermination();
    }
}

