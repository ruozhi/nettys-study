package com.imooc.netty.client.handler;

import com.imooc.netty.protocol.request.LoginRequestPacket;
import com.imooc.netty.protocol.response.LoginResponsePacket;
import com.imooc.netty.session.Session;
import com.imooc.netty.util.LoginUtil;
import com.imooc.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * @author chenmuchao
 * @date 2018/11/28 13:56
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        //创建登陆对象
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setUserId(UUID.randomUUID().toString());
//        loginRequestPacket.setUserName("flash");
//        loginRequestPacket.setPassword("pwd");
//
//        //写数据
//        ctx.channel().writeAndFlush(loginRequestPacket);
//        //ctx.fireChannelActive();
//    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();

        if (loginResponsePacket.isSuccess()) {
            System.out.println("[" + userName + "]登录成功，userId 为: " + loginResponsePacket.getUserId());
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            System.out.println("[" + userName + "]登录失败，原因：" + loginResponsePacket.getReason());
        }
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        System.out.println("客户端连接被关闭!");
    }
}
