package daiwei.geekbang.test.homework.server;

import daiwei.geekbang.test.homework.filter.HeaderAdderFilter;
import daiwei.geekbang.test.homework.filter.HttpFilterChain;
import daiwei.geekbang.test.homework.filter.PathCheckFilter;
import daiwei.geekbang.test.homework.inbound.HttpFilterHandler;
import daiwei.geekbang.test.homework.inbound.HttpRouterHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

/**
 * Created by Daiwei on 2021/1/25
 */
public class MyHttpInitializer extends ChannelInitializer<SocketChannel> {

    private final HttpRouterHandler httpRouterHandler;

    public MyHttpInitializer(List<String> proxyServers) {
        this.httpRouterHandler = new HttpRouterHandler(proxyServers);
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        HttpFilterChain chain = HttpFilterChain.createDefault().addFilter(new PathCheckFilter())
                .addFilter(new HeaderAdderFilter());
        socketChannel.pipeline().addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(1024 * 1024))
                .addLast(new HttpFilterHandler(chain)).addLast(httpRouterHandler);
    }
}
