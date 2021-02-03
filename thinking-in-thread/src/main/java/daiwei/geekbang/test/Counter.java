package daiwei.geekbang.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Daiwei on 2021/2/4
 */
public class Counter implements Runnable{

    private int sum = 0;

    public final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            lock.lock();
            sum += 1;
        } finally {
            lock.unlock();
        }
    }

    public int getSum() {
        return sum;
    }
}
