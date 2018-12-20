package com.imooc.netty.protocol.command;

import com.imooc.netty.protocol.request.LoginRequestPacket;
import com.imooc.netty.protocol.request.MessageRequestPacket;
import com.imooc.netty.protocol.response.LoginResponsePacket;
import com.imooc.netty.protocol.response.MessageResponsePacket;
import com.imooc.netty.serialize.Serializer;
import com.imooc.netty.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.imooc.netty.protocol.command.Command.*;


/**
 * @author chenmuchao
 * @date 2018/11/21 17:30
 */
public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    private PacketCodeC(){
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlogrithm(), serializer);
    }


    /**
     * 编码：封装成二进制的过程
     * 魔数（4字节）+版本号（1字节）+序列化算法（1字节）+指令（1字节）+数据长度（4字节）+数据（N字节）
     * @param packet
     * @return
     */
    public void encode(ByteBuf byteBuf, Packet packet) {
//        //1.创建ByteBuf对象
//        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        //2.序列化Java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        //3.实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public Packet decode(ByteBuf byteBuf) {
        //跳过magic number
        byteBuf.skipBytes(4);
        //跳过版本号
        byteBuf.skipBytes(1);
        //序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();
        //指令
        byte command = byteBuf.readByte();
        //数据长度
        int length = byteBuf.readInt();
         byte[] bytes = new byte[length];
         byteBuf.readBytes(bytes);

         Class<? extends Packet> requestType = getRequestType(command);
         Serializer serializer = getSerializer(serializeAlgorithm);

         if (requestType != null && serializer != null) {
             return serializer.deserialize(requestType, bytes);
         }
        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);

    }

    private Class<? extends Packet> getRequestType(byte command){
        return packetTypeMap.get(command);
    }
}
