package io.daiwei.grpc.service;

import io.daiwei.grpc.AddServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * Created by Daiwei on 2021/3/16
 */
public class AddClientService {

    private ManagedChannel channel;

    private AddServiceGrpc.AddServiceFutureStub stub;

    public AddClientService() {
        channel = ManagedChannelBuilder.forAddress("127.0.0.1", 7777)
                .usePlaintext().build();
        stub = AddServiceGrpc.newFutureStub(channel);
    }

    public AddServiceGrpc.AddServiceFutureStub getStub() {
        return this.stub;
    }
}
