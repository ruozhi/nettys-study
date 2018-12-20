package com.imooc.netty.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 当前用户的回话信息
 * @author chenmuchao
 * @date 2018/11/28 14:37
 */
@Data
@NoArgsConstructor
public class Session {
    /**
     * 用户唯一标识
     */
    private String userId;
    private String userName;

    public Session(String userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString(){
        return userId + ":" + userName;
    }
}
