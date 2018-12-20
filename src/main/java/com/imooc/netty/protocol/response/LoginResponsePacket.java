package com.imooc.netty.protocol.response;

import com.imooc.netty.protocol.command.Packet;
import lombok.Data;

import static com.imooc.netty.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @author chenmuchao
 * @date 2018/11/22 13:48
 */
@Data
public class LoginResponsePacket extends Packet {
    private String userId;
    private String userName;
    private boolean success;
    private String reason;
    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
