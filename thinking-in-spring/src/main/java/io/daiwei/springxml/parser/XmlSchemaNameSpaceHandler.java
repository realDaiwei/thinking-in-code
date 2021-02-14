package io.daiwei.springxml.parser;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by Daiwei on 2021/2/14
 */
public class XmlSchemaNameSpaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("student", new StudentBeanDefinitionParser());
    }
}
