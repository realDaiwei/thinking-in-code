package daiwei.atguigu.tcp.client.handler;

import daiwei.atguigu.tcp.pojo.MsgProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Daiwei on 2021/2/27
 */
public class TcpMessageToByteEncoder extends MessageToByteEncoder<MsgProtocol> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MsgProtocol msgProtocol, ByteBuf byteBuf) throws Exception {
//        System.out.println("encoder invoked");
        byteBuf.writeInt(msgProtocol.getLen());
        byteBuf.writeBytes(msgProtocol.getContent());
    }
}
