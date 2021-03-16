package io.daiwei.cxf.service;

import org.apache.cxf.service.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by Daiwei on 2021/3/17
 */
@WebService(targetNamespace = "http://service.daiwei.io")
public interface GreetService {

    @WebMethod(operationName = "greet")
    String greet(@WebParam(name = "name") String name);
}
