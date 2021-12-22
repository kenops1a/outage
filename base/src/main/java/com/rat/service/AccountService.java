package com.rat.service;

import com.rat.info.JsonResult;
import com.rat.model.UserModel;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/20 16:38
 */
public interface AccountService {

    /**
     * 通过账号密码登录
     * @param userModel 需要参数的属性 email, password
     * @return JsonResult
     */
    JsonResult<String> loginByPassword(UserModel userModel);

    /**
     * 通过邮箱验证码登录
     * @param email 邮箱账号
     * @param verifyCode 验证码
     * @return JsonResult
     */
    JsonResult<String> loginByVerifyCode(String email, String verifyCode);

    /**
     * 退出登录
     * @param email 邮箱账号
     * @return JsonResult
     */
    JsonResult<String> exitLogin(String email);

    /**
     * 注册账号
     * @param userModel 需要赋值的属性：nick，email，password
     * @param verifyCode 邮箱验证码
     * @return JsonResult
     */
    JsonResult<Integer> register(UserModel userModel, String verifyCode);

    /**
     * 注销账号
     * @param userModel 需要赋值的属性：email，password
     * @param verifyCode 邮箱验证码
     * @return JsonResult
     */
    JsonResult<Integer> removeAccount(UserModel userModel, String verifyCode);
}
