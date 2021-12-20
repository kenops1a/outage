package com.rat.service.impl;

import com.rat.info.JsonResult;
import com.rat.service.LoginService;
import com.rat.service.MailService;
import com.rat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/20 16:39
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @Override
    public JsonResult<String> loginByPassword(String email, String password) {

    }

    @Override
    public JsonResult<String> loginByVerifyCode(String email, String verifyCode) {
        return null;
    }
}
