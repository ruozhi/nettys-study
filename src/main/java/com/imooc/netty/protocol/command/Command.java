package com.imooc.netty.protocol.command;

/**
 * @author chenmuchao
 * @date 2018/11/21 16:58
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;
    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;
}
