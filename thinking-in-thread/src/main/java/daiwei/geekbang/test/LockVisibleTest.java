package daiwei.geekbang.test;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Daiwei on 2021/1/24
 */
public class LockVisibleTest {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(3);

        Counter counter = new Counter();

        for (int i = 0; i < 10; i++) {
            service.execute(counter);
        }
        service.shutdown();

        TimeUnit.SECONDS.sleep(1);
        System.out.println(counter.getSum());
    }


}
