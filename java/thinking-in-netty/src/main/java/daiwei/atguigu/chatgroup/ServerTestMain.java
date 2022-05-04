package daiwei.atguigu.chatgroup;

import daiwei.atguigu.chatgroup.server.GroupChatServer;

/**
 * Created by Daiwei on 2021/2/21
 */
public class ServerTestMain {

    public static void main(String[] args) {
        new GroupChatServer(8087).run();
    }
}
