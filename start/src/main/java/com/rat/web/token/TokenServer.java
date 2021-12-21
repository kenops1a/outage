package com.rat.web.token;

import com.rat.model.UserModel;

/**
 * @type: outage
 * @author: yaominc
 * @description: 通过jwt签发token
 * @date: 2021/12/21 16:05
 */
public interface TokenServer {

    /**
     * 给userModel签发token
     * @param userModel 用户对象
     * @return token
     */
    String setToken(UserModel userModel);


    /**
     * 验证token有效性
     * @param token 前端传递的token字符串
     * @return boolean
     */
    Boolean verifyToken(String token);
}
