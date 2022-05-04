package pojo;

import lombok.Data;

/**
 * Created by Daiwei on 2021/3/27
 */
@Data
public class UserSearch {

    private Integer id;

    private Long ts;

    private String name;

    private Integer age;

    private String token;

    private Integer pageSize;

    private Integer page;

}
