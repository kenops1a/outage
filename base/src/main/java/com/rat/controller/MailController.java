package com.rat.controller;

import com.rat.info.JsonResult;
import com.rat.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/20 15:05
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/getVerifyCode")
    public JsonResult<String> getVerifyCode(@RequestParam String email) {
        String title = "什么系统的验证码";
        return mailService.sendEmailVerify(email,title);
    }
}
