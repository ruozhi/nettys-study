package com.imooc.netty.protocol.request;

import com.imooc.netty.protocol.command.Packet;
import lombok.Data;

import static com.imooc.netty.protocol.command.Command.LOGIN_REQUEST;

/**
 * 登陆请求对象
 * @author chenmuchao
 * @date 2018/11/22 13:46
 */
@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
