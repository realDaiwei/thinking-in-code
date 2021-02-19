package io.daiwei.auto.config;

import io.daiwei.auto.condition.AutoCondition;
import io.daiwei.entity.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * Created by Daiwei on 2021/2/19
 */
@Conditional(AutoCondition.class)
public class StarterConfiguration {

    @Bean
    public Student student() {
        Student student = new Student();
        student.setStuId(1L);
        student.setStuAddr("hello road no. 1886 ");
        student.setStuName("helloStarter");
        return student;
    }

}
