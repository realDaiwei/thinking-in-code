package pojo;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Daiwei on 2021/3/27
 */
@Data
@Builder
public class User {

    private Integer id;

    private String username;

    private Integer age;
}
