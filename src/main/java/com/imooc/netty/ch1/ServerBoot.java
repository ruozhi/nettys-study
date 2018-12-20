package com.imooc.netty.ch1;

/**
 * @author chenmuchao
 * @date 2018/11/19 16:51
 */
public class ServerBoot {

    public static final int PORT = 8000;

    public static void main(String[] args) {
        Server server =  new Server(PORT);
        server.start();
    }
}
