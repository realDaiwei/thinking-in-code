package io.daiwei.rpc.pojo.http;

import lombok.*;

/**
 * Created by Daiwei on 2021/3/19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcFxRequest {

    private String serviceClass;

    private String method;

    private Object[] args;

}
