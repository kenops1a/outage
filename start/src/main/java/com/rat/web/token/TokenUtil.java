package com.rat.web.token;

import com.rat.model.UserModel;

/**
 * @type: outage
 * @author: yaominc
 * @description: 通过jwt签发token
 * @date: 2021/12/21 16:05
 */
public interface TokenUtil {

    /**
     * 给userModel签发token
     * @param userModel 用户对象
     * @return token
     */
    String setToken(UserModel userModel);

}
