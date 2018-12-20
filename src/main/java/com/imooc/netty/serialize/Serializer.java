package com.imooc.netty.serialize;

import com.alibaba.fastjson.serializer.JSONSerializable;
import com.imooc.netty.serialize.impl.JSONSerializer;

/**
 * 序列化接口
 * @author chenmuchao
 * @date 2018/11/21 17:01
 */
public interface Serializer {
    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
