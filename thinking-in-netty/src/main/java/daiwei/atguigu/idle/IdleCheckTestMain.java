package daiwei.atguigu.idle;

import daiwei.atguigu.idle.server.IdleCheckNettyServer;
import daiwei.atguigu.idle.server.hanlders.IdleCheckEventHandler;

/**
 * Created by Daiwei on 2021/2/21
 */
public class IdleCheckTestMain {

    public static void main(String[] args) {
        new IdleCheckNettyServer(8087).run();
    }
}
