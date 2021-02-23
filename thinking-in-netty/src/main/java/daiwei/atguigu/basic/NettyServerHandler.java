package daiwei.atguigu.basic;

import daiwei.atguigu.protobuf.message.SchoolMessage;
import daiwei.atguigu.protobuf.message.UserMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * Created by Daiwei on 2021/1/24
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server ctx = " + ctx);
//        ByteBuf buf = (ByteBuf) msg;
//        UserMessage.User user = (UserMessage.User) msg;
        SchoolMessage.schoolData schoolData = (SchoolMessage.schoolData) msg;
//        System.out.println("客户端发送消息是：" + buf.toString(StandardCharsets.UTF_8));
//        System.out.println(user.getId() + " " + user.getName());
        switch (schoolData.getDataType()) {
            case studentType:
                System.out.println(schoolData.getStu().getStuId() + " " + schoolData.getStu().getStuName());
                break;
            case teacherType:
                System.out.println(schoolData.getTea().getTeaName() + " " + schoolData.getTea().getSub());
        }
        System.out.println("客户端地址：" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello client".getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
