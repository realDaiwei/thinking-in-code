package io.daiwei;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Daiwei on 2021/4/28
 */
public class HelloTest {

    private volatile Boolean res;

    private Set<String> set = new HashSet<>();

    private AtomicInteger cnt = new AtomicInteger();

    public void setHello(boolean res) {
        this.res = res;
    }

    public Set<String> getSet() {
        return this.set;
    }

    public AtomicInteger getCnt() {
        return this.cnt;
    }

    public void add(String key) {
        this.set.add(key);
    }

    public void add() {
        this.cnt.incrementAndGet();
    }

    public int getSize() {
        return this.set.size();
    }

    public boolean getHello() {
        return this.res;
    }

    public void printCnt() {
        System.out.println(this.cnt.get());
    }

}

class Hello {

    private Boolean res;

    private Set<String> set;

    private AtomicInteger cnt;

    public Hello(boolean res, Set<String> set, AtomicInteger cnt) {
        this.res = res;
        this.set = set;
        this.cnt = cnt;
    }


    public void print() {
        System.out.println(res);
    }

    public void size() {
        System.out.println(this.set.size());
    }

    public void printCnt() {
        System.out.println(this.cnt.get());
    }

    public void add() {
        this.cnt.getAndIncrement();
    }
}
