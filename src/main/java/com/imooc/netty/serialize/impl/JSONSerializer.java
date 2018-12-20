package com.imooc.netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.imooc.netty.serialize.Serializer;
import com.imooc.netty.serialize.SerializerAlogrithm;

/**
 * @author chenmuchao
 * @date 2018/11/21 17:02
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}
