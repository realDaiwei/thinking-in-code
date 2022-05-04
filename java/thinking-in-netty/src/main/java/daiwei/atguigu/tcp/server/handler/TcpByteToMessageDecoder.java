package daiwei.atguigu.tcp.server.handler;

import daiwei.atguigu.tcp.pojo.MsgProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * Created by Daiwei on 2021/2/27
 */
public class TcpByteToMessageDecoder extends ReplayingDecoder<MsgProtocol> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//        System.out.println("decoder invoked");
        int len = in.readInt();
        byte[] bytes = new byte[len];
        in.readBytes(bytes);
        MsgProtocol msgProtocol = new MsgProtocol(len, bytes);
        out.add(msgProtocol);
    }
}
