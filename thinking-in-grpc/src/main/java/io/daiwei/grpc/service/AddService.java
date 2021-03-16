package io.daiwei.grpc.service;

import io.daiwei.grpc.AddRequest;
import io.daiwei.grpc.AddResponse;
import io.daiwei.grpc.AddServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * Created by Daiwei on 2021/3/16
 */
public class AddService extends AddServiceGrpc.AddServiceImplBase {

    @Override
    public void add(AddRequest request, StreamObserver<AddResponse> responseObserver) {
        int res = add(request.getA(), request.getB());
        responseObserver.onNext(AddResponse.newBuilder().setRes(res).build());
        responseObserver.onCompleted();
    }

    public int add(int a, int b) {
        return a + b;
    }
}
