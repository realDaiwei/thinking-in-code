package daiwei.atguigu.websocket;

import daiwei.atguigu.websocket.server.WebSocketNettyServer;

/**
 * Created by Daiwei on 2021/2/22
 */
public class WebSocketTestMain {

    public static void main(String[] args) {
        new WebSocketNettyServer(8807).run();
    }
}
