package com.imooc.netty.protocol.response;

import com.imooc.netty.protocol.command.Packet;
import lombok.Data;

import static com.imooc.netty.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author chenmuchao
 * @date 2018/11/22 16:21
 */
@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
