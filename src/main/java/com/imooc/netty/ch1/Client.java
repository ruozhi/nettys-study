package com.imooc.netty.ch1;

import java.io.IOException;
import java.net.Socket;

/**
 * @author chenmuchao
 * @date 2018/11/19 17:06
 */
public class Client {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8000;
    public static final int SLEEP_TIME = 500;

    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket(HOST, PORT);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("客户端启动成功!");
                while (true) {
                    try {
                        String message = "hello world";
                        System.out.println("客户端发送数据：" +  message);
                        socket.getOutputStream().write(message.getBytes());
                    } catch (Exception e) {
                        System.out.println("写数据出错");
                    }
                    sleep();
                }
            }
        }).start();
    }
    private static void sleep(){
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
