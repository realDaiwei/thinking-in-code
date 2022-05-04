package daiwei.atguigu.basic;

import daiwei.atguigu.protobuf.message.SchoolMessage;
import daiwei.atguigu.protobuf.message.UserMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * Created by Daiwei on 2021/1/24
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("client " + ctx);
//        ctx.writeAndFlush(Unpooled.copiedBuffer("hello server".getBytes(StandardCharsets.UTF_8)));
//        UserMessage.User user = UserMessage.User.newBuilder().setId(1L).setName("daiwei").build();
        int rnd = new Random().nextInt(100) % 2;
        SchoolMessage.schoolData msg = null;
        if (rnd == 1) {
            msg = SchoolMessage.schoolData.newBuilder().setDataType(SchoolMessage.schoolData.DataType.studentType)
                    .setStu(SchoolMessage.Student.newBuilder().setStuId(2L).setGrade(1).setStuName("hello").build()).build();
        } else {
            msg = SchoolMessage.schoolData.newBuilder().setDataType(SchoolMessage.schoolData.DataType.teacherType)
                    .setTea(SchoolMessage.Teacher.newBuilder().setTeaId(2L).setTeaName("world").setSub("语文").build()).build();
        }
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("server reply: "+ buf.toString(StandardCharsets.UTF_8));
        System.out.println("server addr: " + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel();
    }
}
