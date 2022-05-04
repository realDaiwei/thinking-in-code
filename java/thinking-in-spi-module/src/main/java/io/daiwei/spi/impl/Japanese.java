package io.daiwei.spi.impl;

import io.daiwei.spi.service.Person;

/**
 * Created by Daiwei on 2021/5/2
 */
public class Japanese implements Person {
    @Override
    public void eat(String food) {
        System.out.println("Japanese eat "+ food);
    }

    @Override
    public void drink(String water) {
        System.out.println("Japanese drink " + water);
    }

    @Override
    public void sleep() {
        System.out.println("Japanese sleep !");
    }

    @Override
    public void work(String job) {
        System.out.println("Japanese do !" + job);
    }
}
