package io.daiwei.spi;

import io.daiwei.spi.service.Person;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by Daiwei on 2021/5/2
 */
public class SpiTest {

    public void test() {
        ServiceLoader<Person> load = ServiceLoader.load(Person.class);
        Iterator<Person> iterator = load.iterator();
        while (iterator.hasNext()) {
            Person next = iterator.next();
            next.eat("nice beef!");
        }
    }
}
