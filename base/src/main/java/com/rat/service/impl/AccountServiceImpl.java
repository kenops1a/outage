package com.rat.service.impl;

import com.rat.cache.RedisUtil;
import com.rat.info.JsonResult;
import com.rat.info.ResultCode;
import com.rat.info.ResultTool;
import com.rat.mapper.UserMapper;
import com.rat.model.UserModel;
import com.rat.service.AccountService;
import com.rat.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/20 16:39
 */
@Service
public class AccountServiceImpl implements AccountService {
    /**
     * 用户状态常量
     */
    private static final String USER_STATUS_LOCKED = "0";
    private static final String USER_STATUS_NORMAL = "1";
    private static final String USER_STATUS_DELETE = "2";
    /**
     * 模块名，方法名，参数常量
     */
    private static final String PREFIX = "outage";
    private static final String TOKEN = "token";
    private static final String VERIFY_CODE = "verifyCode";

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public JsonResult<String> loginByPassword(UserModel userModel) {
        UserModel userVerify = userMapper.getUserByEmail(userModel.getEmail());
        // 判断查询到的用户密码是否与传递密码相同
        // 生成token令牌并存入redis，有效时长为10小时
        // 设置token的key
        String key = redisUtil.keyMaker(PREFIX,TOKEN,userModel.getEmail());
        String token = tokenService.setToken(userVerify);
        // 将token存入redis
        redisUtil.setCache(key,token,36000);
        // 返回带有token的json数据
        return ResultTool.success(token);
    }

    @Override
    public JsonResult<String> loginByVerifyCode(String email, String verifyCode) {
        UserModel userVerify = userMapper.getUserByEmail(email);
        // 生成用来查询的key
        // 查询邮箱验证码key
        String verifyCodeKey = redisUtil.keyMaker(PREFIX,VERIFY_CODE, userVerify.getEmail());
        // 设置token key
        String tokenKey = redisUtil.keyMaker(PREFIX,TOKEN, userVerify.getEmail());
        if (!redisUtil.hasKey(verifyCodeKey) || redisUtil.getCache(verifyCodeKey).equals(verifyCode)){
            // 判断redis中是否存在key为email的键值对以及该key对应的值是否为verifyCode
            return ResultTool.faild(ResultCode.VERIFY_CODE_LATER_OR_FALSE);
        } else {
            // 生成token令牌并存入redis，有效时长为10小时
            String token = tokenService.setToken(userVerify);
            redisUtil.setCache(tokenKey,token,36000);
            return ResultTool.success(token);
        }
    }

    @Override
    public JsonResult<String> exitLogin(String email) {
        String tokenKey = redisUtil.keyMaker(PREFIX,TOKEN,email);
        // 判断用户token是否存在
        if (!redisUtil.hasKey(tokenKey)) {
            return ResultTool.faild("错误，用户未登录");
        } else {
            redisUtil.deleteCache(tokenKey);
            return ResultTool.success("退出成功！");
        }
    }
}
