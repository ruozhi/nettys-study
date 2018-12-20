package com.imooc.netty.server.handler;

import com.imooc.netty.protocol.request.MessageRequestPacket;
import com.imooc.netty.protocol.response.MessageResponsePacket;
import com.imooc.netty.session.Session;
import com.imooc.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * 消息处理handler
 * @author chenmuchao
 * @date 2018/11/28 14:13
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        //1.拿到消息发送方的会话消息
        Session session = SessionUtil.getSession(ctx.channel());
        //2.通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());
        //System.out.println(new Date() + ":收到客户端消息：" + messageRequestPacket.getMessage());
        //messageResponsePacket.setMessage("服务端回复【"+messageRequestPacket.getMessage()+"】");

        //3.拿到消息接收方的channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        //4.将消息发送到消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.out.println("["+messageRequestPacket.getToUserId()+"]不在线，发送失败");
        }

    }
}
