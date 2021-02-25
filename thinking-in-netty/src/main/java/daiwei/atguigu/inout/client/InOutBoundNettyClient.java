package daiwei.atguigu.inout.client;

import daiwei.atguigu.inout.client.handler.MyLongToByteEncoder;
import daiwei.atguigu.inout.client.handler.MyClientInboundHandler;
import daiwei.atguigu.inout.server.handler.MyByteToLongDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Daiwei on 2021/2/25
 */
public class InOutBoundNettyClient {

    private String host;

    private Integer port;

    public InOutBoundNettyClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        EventLoopGroup group = new NioEventLoopGroup(2);
        try {
            Bootstrap bootstrap =new Bootstrap();
            bootstrap.option(ChannelOption.SO_BACKLOG, 128);
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new MyLongToByteEncoder())
                                    .addLast(new MyByteToLongDecoder())
                                    .addLast(new MyClientInboundHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect(this.host, this.port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
