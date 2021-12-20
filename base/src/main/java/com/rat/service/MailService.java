package com.rat.service;

import com.rat.info.JsonResult;
import org.springframework.stereotype.Service;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/20 14:33
 */
public interface MailService {
    /**
     * 发送邮件
     * @param to 接收者
     * @param title 邮件主题
     * @return JsonResult
     */
    JsonResult<String> sendEmailVerify(String to, String title);
}
