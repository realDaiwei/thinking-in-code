package daiwei.atguigu.chatgroup.service.handlers;

import daiwei.atguigu.chatgroup.util.DateFormatUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;

/**
 * Created by Daiwei on 2021/2/21
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel channel = channelHandlerContext.channel();
        channels.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush("[客户端 "+ channel.remoteAddress() +"]["+ DateFormatUtil.nowStr() + "] 说："+ s );
            }
        });
    }

    /**
     * 连接一旦建立，会第一个被调用执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush("[客户端 "+ channel.remoteAddress() +"]["+ DateFormatUtil.nowStr() + "] 加入聊天");
        channels.add(channel);
    }

    /**
     * 某个链接活跃提示上线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String msg = "[客户端 "+ channel.remoteAddress() +"]["+ DateFormatUtil.nowStr() + "] 上线了～";
        System.out.println(msg);
    }

    /**
     * 表示 Channel 处于不活跃状态，业务上提示离线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String msg = "[客户端 "+ channel.remoteAddress() +"]["+ DateFormatUtil.nowStr() + "] 下线了～";
        System.out.println(msg);
    }
}

