package io.daiwei.rpc.demo.pojo;

import lombok.*;

/**
 * Created by Daiwei on 2021/3/20
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    private String userName;

    private Integer age;

}
