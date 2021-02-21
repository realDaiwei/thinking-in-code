package daiwei.atguigu.chatgroup;

import daiwei.atguigu.chatgroup.client.GroupChatClient;

/**
 * 客户端测试main
 * Created by Daiwei on 2021/2/21
 */
public class ClientTestMain {

    public static void main(String[] args) {
        new GroupChatClient("127.0.0.1", 8087).run();
    }
}
