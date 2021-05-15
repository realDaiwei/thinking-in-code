package io.daiwei.jvm.test;

/**
 * Created by Daiwei on 2021/5/7
 */
public class HiClass extends HelloClass implements GoodNight {

    @Override
    public void hello() {
        super.hello();
    }

    @Override
    public String hiYou(String you) {
        return super.hiYou(you);
    }

    @Override
    public void goodNight(String name) {
        System.out.println("good night");
    }
}
