package com.rat.service.impl;

import com.rat.cache.RedisUtil;
import com.rat.info.JsonResult;
import com.rat.info.ResultCode;
import com.rat.info.ResultTool;
import com.rat.mapper.UserMapper;
import com.rat.model.UserModel;
import com.rat.service.LoginService;
import com.rat.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/20 16:39
 */
@Service
public class LoginServiceImpl implements LoginService {
    private final String USER_STATUS_LOCKED = "0";
    private final String USER_STATUS_NORMAL = "1";
    private final String USER_STATUS_DELETE = "2";
    @Autowired
    private MailService mailService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public JsonResult<String> loginByPassword(UserModel userModel) {
        UserModel userVerify = userMapper.getUserByEmail(userModel.getEmail());
        if (userVerify.getPassword().equals(userModel.getPassword())) {
            // 判断查询到的用户密码是否与传递密码相同
            return ResultTool.success("登录成功");
        } else {
            return ResultTool.faild(ResultCode.USER_PASSWORD_ERROR);
        }
    }

    @Override
    public JsonResult<String> loginByVerifyCode(String email, String verifyCode) {
        UserModel userVerify = userMapper.getUserByEmail(email);
         if (!redisUtil.hasKey(email) || redisUtil.getCache(email).equals(verifyCode)){
            // 判断redis中是否存在key为email的键值对以及该key对应的值是否为verifyCode
            return ResultTool.faild(ResultCode.VERIFY_CODE_LATER_OR_FALSE);
        } else {
            return ResultTool.success("登录成功");
        }
    }
}
