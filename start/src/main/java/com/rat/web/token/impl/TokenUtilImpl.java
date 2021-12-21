package com.rat.web.token.impl;

import com.rat.model.UserModel;
import com.rat.web.token.TokenUtil;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import java.util.Date;

/**
 * @type: outage
 * @author: yaominc
 * @description: TokenUtil实现类
 * @date: 2021/12/21 16:07
 */
public class TokenUtilImpl implements TokenUtil {
    @Override
    public String setToken(UserModel userModel) {
        Date start = new Date();
        // token有效期，1小时
        long lifeTime = System.currentTimeMillis() + 60 * 60 * 1000;
        Date end = new Date(lifeTime);
        String token =
        return null;
    }
}
