package io.daiwei.cxf.service.impl;

import io.daiwei.cxf.service.GreetService;
import org.springframework.stereotype.Component;

/**
 * Created by Daiwei on 2021/3/17
 */
@Component
public class GreetServiceImpl implements GreetService {

    @Override
    public String greet(String name) {
        return "hello " + name;
    }
}
