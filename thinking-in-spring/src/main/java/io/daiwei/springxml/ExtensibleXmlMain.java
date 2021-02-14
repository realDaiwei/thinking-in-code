package io.daiwei.springxml;

import io.daiwei.springxml.entity.Student;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Created by Daiwei on 2021/2/14
 */
@Log4j
public class ExtensibleXmlMain {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        reader.loadBeanDefinitions("META-INF/extensible-xml-context.xml");

        Student stu = beanFactory.getBean(Student.class);

        log.info(stu);
    }
}
