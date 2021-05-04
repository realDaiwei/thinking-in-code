package io.daiwei.spi.impl;

import io.daiwei.spi.service.Person;

/**
 * Created by Daiwei on 2021/5/2
 */
public class Chinese implements Person {

    @Override
    public void eat(String food) {
        System.out.println(food + "rice noodles and pork! chinese food is amazing!");
    }

    @Override
    public void drink(String water) {
        System.out.println("bear, baijiu and juice." + water);
    }

    @Override
    public void sleep() {
        System.out.println("Chinese sleeping!");
    }

    @Override
    public void work(String job) {
        System.out.println(job +  "chinese work hard!");
    }
}
