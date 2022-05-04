package daiwei.atguigu.rpc.client;

import daiwei.atguigu.rpc.client.handler.MyRpcClientChannelHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Daiwei on 2021/2/27
 */
public class RpcNettyClient {

    private static final ExecutorService executorServer = Executors.newSingleThreadExecutor();

    private MyRpcClientChannelHandler handler;

    private final String host;

    private final int port;

    public RpcNettyClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public Object getBean(final Class<?> clazz, String providerName) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{clazz}, (proxy, method, args) -> {
            if (handler == null) {
                run();
            }
            handler.setParam(providerName + args[0]);
            return executorServer.submit(handler).get();
        });
    }

    private void run() {
        handler = new MyRpcClientChannelHandler();
        EventLoopGroup group = new NioEventLoopGroup(2);

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group).option(ChannelOption.SO_BACKLOG, 128);
            bootstrap.channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new StringEncoder())
                                    .addLast(new StringDecoder()).addLast(handler);
                        }
                    });
            bootstrap.connect(this.host, this.port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
