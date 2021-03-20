package io.daiwei.rpc.pojo.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.intellij.lang.annotations.JdkConstants;

/**
 * Created by Daiwei on 2021/3/19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcFxResponse {

    private int code;

    private Object data;

    private Exception exception;
}
