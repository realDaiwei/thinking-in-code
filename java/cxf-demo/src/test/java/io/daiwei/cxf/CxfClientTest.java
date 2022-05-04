package io.daiwei.cxf;

import io.daiwei.cxf.service.GreetService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.jupiter.api.Test;

/**
 * Created by Daiwei on 2021/3/17
 */

public class CxfClientTest {

    @Test
    public void test() {
        String addr = "http://localhost:8080/soap/greet?wsdl";
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress(addr);
        jaxWsProxyFactoryBean.setServiceClass(GreetService.class);
        GreetService bean = (GreetService) jaxWsProxyFactoryBean.create();
        String res = bean.greet("daiwei");
        System.out.println(res);
    }

}
