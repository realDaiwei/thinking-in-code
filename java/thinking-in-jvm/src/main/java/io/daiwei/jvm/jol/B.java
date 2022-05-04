package io.daiwei.jvm.jol;

/**
 * Created by Daiwei on 2021/5/14
 */
public class B {

    private String hello;

    private int a;

    private boolean b;

    @Override
    public int hashCode() {
        return super.hashCode() + 1;
    }
}
