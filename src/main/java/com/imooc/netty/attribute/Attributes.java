package com.imooc.netty.attribute;

import com.imooc.netty.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author chenmuchao
 * @date 2018/11/22 16:23
 */
public interface Attributes {
    /**
     * 定义是否登陆成功的标志
     */
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
