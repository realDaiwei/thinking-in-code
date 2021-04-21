package daiwei.atguigu.idle.server.hanlders;

import daiwei.atguigu.chatgroup.server.handlers.ServerInitHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by Daiwei on 2021/2/21
 */
public class IdleCheckServerInitHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new IdleStateHandler(0, 0, 3, TimeUnit.SECONDS))
                .addLast(new IdleCheckEventHandler());
    }
}
