package com.imooc.netty.util;


import com.imooc.netty.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author chenmuchao
 * @date 2018/11/22 16:31
 */
public class LoginUtil {
    /**
     * 设置登陆标识
     * @param channel
     */
    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
