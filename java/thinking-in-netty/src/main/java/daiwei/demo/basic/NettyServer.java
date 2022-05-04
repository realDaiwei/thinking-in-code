package daiwei.demo.basic;

import daiwei.atguigu.idle.server.hanlders.IdleCheckEventHandler;
import daiwei.atguigu.idle.server.hanlders.IdleCheckServerInitHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Created by Daiwei on 2021/9/21
 */
public class NettyServer {

    public static void main(String[] args) {
        int port = 8801;
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 512)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new IdleStateHandler(2, 5, 8, TimeUnit.SECONDS))
                                    .addLast(new HelloHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("服务启动完成, 监听端口 http://127.0.0.1:" + port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}

/**
 * helloHandler 处理器
 */
class HelloHandler extends ChannelInboundHandlerAdapter {

    /**
     * 从channel中读数据。执行一些业务操作，或者加入一些hook触发后续业务。
     * 同时可以通过channel发送数据
     * @param ctx 上下文
     * @param msg 从channel中读到的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("message["+ byteBuf.toString(StandardCharsets.UTF_8) +"] received！and " +
                "message is from [" + ctx.channel().remoteAddress().toString() + "]");
        ByteBuf toClientMsg = Unpooled.copiedBuffer( "hello!".getBytes(StandardCharsets.UTF_8));
        ctx.channel().writeAndFlush(toClientMsg);
    }

    /**
     * 事件触发方法
     * @param ctx 上下文
     * @param evt 事件
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            IdleState state = event.state();
            System.out.println(state.toString());
            if (IdleState.ALL_IDLE.equals(state)) {
                System.out.println("all idle and close channel");
                ctx.channel().close();
            }
        }
    }

    /**
     * 在处理过程中捕捉到了异常执行这个方法，输出异常，关闭资源
     * @param ctx 上下文
     * @param cause 异常信息
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }
}

