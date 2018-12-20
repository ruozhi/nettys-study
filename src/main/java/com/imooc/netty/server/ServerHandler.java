package com.imooc.netty.server;

import com.imooc.netty.protocol.command.Packet;
import com.imooc.netty.protocol.command.PacketCodeC;
import com.imooc.netty.protocol.request.LoginRequestPacket;
import com.imooc.netty.protocol.request.MessageRequestPacket;
import com.imooc.netty.protocol.response.LoginResponsePacket;
import com.imooc.netty.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.Buffer;
import java.util.Date;

/**
 * @author chenmuchao
 * @date 2018/11/22 14:18
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){

        ByteBuf requestByteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        if (packet instanceof LoginRequestPacket) {
            System.out.println(new Date() + ": 收到客户端登陆请求...");
            // 登录流程
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": XX登录成功!");
            } else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }
            // 登录响应
            //ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            //ctx.channel().writeAndFlush(responseByteBuf);
        } else if(packet instanceof MessageRequestPacket) {
            //客户端发来消息
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            System.out.println(new Date() + ": 收到客户端消息：" + messageRequestPacket.getMessage());
            messageResponsePacket.setMessage("服务端回复【"+messageRequestPacket.getMessage()+"】");
            //ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            //ctx.channel().writeAndFlush(responseByteBuf);

        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
