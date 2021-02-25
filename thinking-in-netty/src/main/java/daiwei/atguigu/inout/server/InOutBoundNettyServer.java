package daiwei.atguigu.inout.server;

import daiwei.atguigu.inout.client.handler.MyLongToByteEncoder;
import daiwei.atguigu.inout.server.handler.MyByteToLongDecoder;
import daiwei.atguigu.inout.server.handler.MyServerInboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Daiwei on 2021/2/25
 */
public class InOutBoundNettyServer {

    private int port;

    public InOutBoundNettyServer(int port) {
        this.port = port;
    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
//            bootstrap.option(ChannelOption.SO_BACKLOG, 128);
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            pipeline.addLast(new MyByteToLongDecoder())
                                    .addLast(new MyLongToByteEncoder())
                                    .addLast(new MyServerInboundHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(this.port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
