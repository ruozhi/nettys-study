package com.imooc.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * 客户端处理器，负责向服务器写数据
 * @author chenmuchao
 * @date 2018/11/21 10:56
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 方法会在客户端连接建立成功之后被调用
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ":客户端写出数据");
        //1.获取数据
        ByteBuf byteBuf = getByteBuf(ctx);
        //2.写数据
        ctx.channel().writeAndFlush(byteBuf);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){

        //1.获取一个二进制抽象ByteBuf
        ByteBuf byteBuf =ctx.alloc().buffer();
        //2.准备数据，指定字符串的字符集为utf-8
        byte[] bytes = "你好，Ztesoft!".getBytes(Charset.forName("utf-8"));
        //3.填充数据到ByteBuf
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ": 客户端读到数据-> " + byteBuf.toString(Charset.forName("utf-8")));
    }
}
