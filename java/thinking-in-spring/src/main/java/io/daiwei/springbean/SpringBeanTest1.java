package io.daiwei.springbean;

import io.daiwei.springbean.entity.Student;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Daiwei on 2021/5/1
 */
public class SpringBeanTest1 {


    public static void main(String[] args) {
        AbstractApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-context.xml");
        Student bean = beanFactory.getBean(Student.class);
        bean.sayHi();
    }
}
