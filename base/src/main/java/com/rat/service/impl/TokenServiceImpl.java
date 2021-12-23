package com.rat.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rat.model.UserModel;
import com.rat.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @type: outage
 * @author: yaominc
 * @description: TokenUtil实现类
 * @date: 2021/12/21 16:07
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    /**
     * EXPIRE_TIME:token有效时间10小时
     * TOKEN_SECRET:密钥盐值
     */
    private static final long EXPIRE_TIME = 10*60*60*1000;
    private static final String TOKEN_SECRET = "what;sup";
    private int index = 0;
    @Override
    public String setToken(UserModel userModel) {
        String token = null;
        try {
            // 到期时间
            Date expireAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    // 发行者
                    .withIssuer("auth0")
                    .withClaim("email",userModel.getEmail())
                    .withExpiresAt(expireAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException| JWTCreationException je) {
            log.info("token签发失败");
        }
        index++;
        System.out.println(index);
        log.info(token);
        return token;
    }

    @Override
    public Boolean verifyToken(String token) {
        try {
            // 创建token验证器
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = jwtVerifier.verify(token);
            log.info("请求者email：" + jwt.getClaim("email").asString());
            log.info("token过期时间：" + jwt.getExpiresAt());
        } catch (IllegalArgumentException| JWTVerificationException je) {
            // 认证失败
            log.info("认证失败");
            return false;
        }
        return true;
    }
}
