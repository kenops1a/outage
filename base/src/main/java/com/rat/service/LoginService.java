package com.rat.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rat.info.JsonResult;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/20 16:38
 */
public interface LoginService {

    /**
     * 通过账号密码登录
     * @param email 邮箱账号
     * @param password 密码
     * @return true/false
     */
    JsonResult<String> loginByPassword(String email, String password);

    JsonResult<String> loginByVerifyCode(String email, String verifyCode);
}
