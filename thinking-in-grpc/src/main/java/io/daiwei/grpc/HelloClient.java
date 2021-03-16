package io.daiwei.grpc;

import com.google.common.util.concurrent.ListenableFuture;
import io.daiwei.grpc.service.AddClientService;

import java.util.concurrent.ExecutionException;

/**
 * Created by Daiwei on 2021/3/16
 */
public class HelloClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int a = 100, b = 200;
        AddClientService service = new AddClientService();
        ListenableFuture<AddResponse> add = service.getStub().add(AddRequest.newBuilder().setA(a).setB(b).build());
        AddResponse addResponse = add.get();
        System.out.println(addResponse.getRes());
    }

}
