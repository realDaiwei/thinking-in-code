package daiwei.atguigu.rpc.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Daiwei on 2021/2/27
 */
public class MyRpcClientChannelHandler extends ChannelInboundHandlerAdapter implements Callable {

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    private ChannelHandlerContext ctx;

    private String res;

    private String param;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }


    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("read!");
        res = msg.toString();
        notifyAll();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }

    @Override
    public synchronized Object call() throws Exception {
        System.out.println("call！！");
        ctx.writeAndFlush(this.param);
        wait();
        return this.res;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
