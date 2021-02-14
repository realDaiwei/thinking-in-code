package io.daiwei.springxml.parser;

import io.daiwei.springxml.entity.Student;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Created by Daiwei on 2021/2/14
 */
public class StudentBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Student.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        setAttribute("stuName", element, builder);
        setAttribute("stuAddr", element, builder);
        setAttribute("stuId", element, builder);
    }

    private void setAttribute(String attrKey, Element element, BeanDefinitionBuilder builder) {
        String attrValue = element.getAttribute(attrKey);
        if (StringUtils.hasText(attrValue)) {
            builder.addPropertyValue(attrKey, attrValue);
        }
    }
}
