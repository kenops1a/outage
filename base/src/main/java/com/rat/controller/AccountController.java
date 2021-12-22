package com.rat.controller;

import com.rat.info.JsonResult;
import com.rat.info.ResultCode;
import com.rat.info.ResultTool;
import com.rat.model.UserModel;
import com.rat.service.AccountService;
import com.rat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/20 16:38
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;

    /**
     * 通过账号密码登录
     * @param userModel 需要值的属性： email，password
     * @return JsonResult
     */
    @RequestMapping(value = "/loginByPasswd", method = RequestMethod.POST)
    public JsonResult<String> loginByPassword(@RequestBody UserModel userModel) {
        // 判断参数是否为空
        if ("".equals(userModel.getEmail()) || "".equals(userModel.getPassword())) {
            return ResultTool.faild(ResultCode.ITEM_ALREADY_EXIST);
        }

        // 判断用户账号状态是否正常
        JsonResult<String> jsonResult = userService.getUserStatus(userModel.getEmail());
        if (jsonResult.getSuccess()) {
            return jsonResult;
        }

        // 返回校验结果
        else {
            return accountService.loginByPassword(userModel);
        }
    }

    /**
     * 通过邮箱账号和邮箱验证码登录
     * @param map 键值对，存储email和verifyCode
     * @return JsonResult
     */
    @RequestMapping(value = "/loginByVerify", method = RequestMethod.POST)
    public JsonResult<String> loginByVerifyCode(@RequestBody Map<String,String> map) {
        // 判断参数是否为空
        if ("".equals(map.get("email")) || "".equals(map.get("verifyCode"))) {
            return ResultTool.faild(ResultCode.PARAM_IS_BLANK);
        }
        // 判断用户账号状态是否正常
        JsonResult<String> jsonResult = userService.getUserStatus(map.get("email"));
        if (!jsonResult.getSuccess()) {
            return jsonResult;
        }
        // 返回验证结果
        else {
            return accountService.loginByVerifyCode(map.get("email"),map.get("verigyCode"));
        }
    }

    /**
     * 退出登录，清除redis中的token
     * @param email 邮箱账号
     * @return JsonResult
     */
    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public JsonResult<String> exitLogin(@RequestParam String email) {
        if ("".equals(email)) {
            return ResultTool.faild(ResultCode.PARAM_IS_REQUIRED);
        } else {
            return accountService.exitLogin(email);
        }
    }

    /**
     * 注册账号
     * @param userModel 需要赋值的属性，nick，email，password
     * @return JsonResult
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JsonResult<Integer> register(@Valid @RequestBody UserModel userModel, @RequestParam String verifyCode) {
        // 判断验证码是否为空，userModel完整性由@Valid注解校验
        if ("".equals(verifyCode)) {
            return ResultTool.faild(ResultCode.PARAM_IS_REQUIRED);
        } else {
            return accountService.register(userModel, verifyCode);
        }
    }

    /**
     * 注销账号
     * @param userModel 需要赋值的属性：email，password
     * @param verifyCode 验证码
     * @return JsonResult
     */
    @RequestMapping(value = "/removeAccount", method = RequestMethod.POST)
    public JsonResult<Integer> removeAccount(@RequestBody UserModel userModel, @RequestParam String verifyCode) {
        // 校验参数完整性
        if ("".equals(verifyCode) || userModel.getEmail() == null || userModel.getPassword() == null ) {
            return ResultTool.faild(ResultCode.PARAM_IS_REQUIRED);
        } else {
            return accountService.removeAccount(userModel,verifyCode);
        }
    }
}
