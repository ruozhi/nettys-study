package com.imooc.netty.protocol.request;

import com.imooc.netty.protocol.command.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.imooc.netty.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author chenmuchao
 * @date 2018/11/22 16:19
 */
@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
    /**
     * 表示要发给哪个用户
     */
    private String toUserId;
    private String message;

    public MessageRequestPacket(String toUserId, String message){
        this.toUserId = toUserId;
        this.message = message;
    }
    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
