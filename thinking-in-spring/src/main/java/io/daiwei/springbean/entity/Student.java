package io.daiwei.springbean.entity;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Daiwei on 2021/5/1
 */
@Data
public class Student implements DisposableBean {

    private Integer id;

    private String name;

    public void sayHi() {
        System.out.println("hello~");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("close");
    }

}
