package com.imooc.netty.server.handler;

import com.imooc.netty.util.LoginUtil;
import com.imooc.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author chenmuchao
 * @date 2018/11/28 14:04
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        if (!SessionUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        if (SessionUtil.hasLogin(ctx.channel())) {
            System.out.println("当前连接登陆验证完毕，无需再次验证，AuthHandler 被移除");
        } else {
            System.out.println("无登陆验证，强制关闭连接！");
        }
    }
}
