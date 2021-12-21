package com.rat.controller;

import com.rat.info.JsonResult;
import com.rat.info.ResultCode;
import com.rat.info.ResultTool;
import com.rat.model.UserModel;
import com.rat.service.LoginService;
import com.rat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/20 16:38
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/byPassword", method = RequestMethod.POST)
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
            return loginService.loginByPassword(userModel);
        }
    }

    @RequestMapping(value = "/byVerifyCode", method = RequestMethod.POST)
    public JsonResult<String> loginByVerifyCode(@RequestBody Map<String,String> map) {
        // 判断参数是否为空
        if ("".equals(map.get("email")) || "".equals(map.get("verifyCode"))) {
            return ResultTool.faild(ResultCode.ITEM_ALREADY_EXIST);
        }
        // 判断用户账号状态是否正常
        JsonResult<String> jsonResult = userService.getUserStatus(map.get("email"));
        if (jsonResult.getSuccess()) {
            return jsonResult;
        }

        // 返回验证结果
        else {
            return loginService.loginByVerifyCode(map.get("email"),map.get("verigyCode"));
        }
    }
}
