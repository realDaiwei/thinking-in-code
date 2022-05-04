package daiwei.atguigu.inout.client.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

/**
 * Created by Daiwei on 2021/2/25
 */
public class MyClientInboundHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Long aLong) throws Exception {
        System.out.println("reply from server: " + aLong);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientInboundHandler sending data..");
//        ctx.writeAndFlush(12345678L);
//        ctx.writeAndFlush(Unpooled.copiedBuffer("abcdefghijklmnopqrst", StandardCharsets.UTF_8));
        ctx.writeAndFlush(1234567812345678L);
    }
}
