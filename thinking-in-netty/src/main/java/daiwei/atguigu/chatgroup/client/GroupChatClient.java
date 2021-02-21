package daiwei.atguigu.chatgroup.client;

import daiwei.atguigu.chatgroup.client.handlers.ClientInitHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * 简单群聊的客户端部分
 * Created by Daiwei on 2021/2/21
 */
public class GroupChatClient {

    private final String host;

    private final int port;

    public GroupChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {

        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors).channel(NioSocketChannel.class)
                    .handler(new ClientInitHandler());
            ChannelFuture future = bootstrap.connect(this.host, this.port).sync();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                future.channel().writeAndFlush(str);
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventExecutors.shutdownGracefully();
        }

    }
}
