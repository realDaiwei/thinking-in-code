package daiwei.atguigu.tcp.server.handler;

import daiwei.atguigu.tcp.pojo.MsgProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Created by Daiwei on 2021/2/27
 */
public class TcpServerHandler extends SimpleChannelInboundHandler<MsgProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MsgProtocol msg) throws Exception {
        System.out.println("read from client data " + new String(msg.getContent(), StandardCharsets.UTF_8));
        for (int i = 0; i < 10; i++) {
            String str = UUID.randomUUID().toString();
            MsgProtocol protocol = new MsgProtocol(str.length(), str.getBytes(StandardCharsets.UTF_8));
            channelHandlerContext.writeAndFlush(protocol);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.channel().close();
    }
}
