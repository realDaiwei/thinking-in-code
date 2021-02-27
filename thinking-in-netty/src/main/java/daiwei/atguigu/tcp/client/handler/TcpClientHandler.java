package daiwei.atguigu.tcp.client.handler;

import daiwei.atguigu.tcp.pojo.MsgProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by Daiwei on 2021/2/27
 */
public class TcpClientHandler extends SimpleChannelInboundHandler<MsgProtocol> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            String msg = "hello netty tcp " + i;
            MsgProtocol protocol = new MsgProtocol(msg.length(), msg.getBytes(StandardCharsets.UTF_8));
            ctx.writeAndFlush(protocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MsgProtocol msgProtocol) throws Exception {
        System.out.println("receive data from server: " + new String(msgProtocol.getContent(), StandardCharsets.UTF_8));
    }
}
