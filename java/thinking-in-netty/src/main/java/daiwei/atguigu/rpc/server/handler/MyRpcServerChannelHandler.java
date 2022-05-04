package daiwei.atguigu.rpc.server.handler;

import daiwei.atguigu.rpc.common.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by Daiwei on 2021/2/27
 */
public class MyRpcServerChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg.toString() + "!!!");
        if (msg.toString().startsWith("HelloService#hello#")) {
            String param = msg.toString().substring(msg.toString().lastIndexOf("#") + 1);
            String hello = new HelloServiceImpl().hello(param);
            ctx.writeAndFlush(hello);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
