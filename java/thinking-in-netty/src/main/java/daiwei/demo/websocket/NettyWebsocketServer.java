package daiwei.demo.websocket;

import daiwei.atguigu.chatgroup.util.DateFormatUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by Daiwei on 2021/9/25
 */
public class NettyWebsocketServer {

    public static void main(String[] args) {
        int port = 8002;
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new HttpServerCodec())
                                    .addLast(new HttpObjectAggregator(1024 * 1024))
                                    .addLast(new WebSocketServerProtocolHandler("/websocket"))
                                    .addLast(new MyWebSocketHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            System.out.println("netty websocket server started and listening at port " + port);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

/**
 * 业务处理的ChannelHandler
 */
class MyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("receive message ["+ msg.text() +"] from web ");
        ctx.channel().writeAndFlush(new TextWebSocketFrame("[" + DateFormatUtil.nowStr() + "]: 服务端收到消息[" + msg.text() + "]"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
