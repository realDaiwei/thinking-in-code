package io.daiwei.cxf.config;

import io.daiwei.cxf.service.GreetService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.xml.ws.Endpoint;

/**
 * Created by Daiwei on 2021/3/17
 */
@Configuration
public class WebServiceConfig {

    @Resource
    private Bus bus;

    @Resource
    private GreetService greetService;

    @Bean
    public ServletRegistrationBean dispatchServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
    }

    @Bean
    public Endpoint greetEndPoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, greetService);
        endpoint.publish("/greet");
        return endpoint;
    }
}
