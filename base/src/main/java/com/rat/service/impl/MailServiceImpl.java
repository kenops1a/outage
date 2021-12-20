package com.rat.service.impl;

import com.rat.info.JsonResult;
import com.rat.info.ResultTool;
import com.rat.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/20 14:33
 */
@Service
public class MailServiceImpl implements MailService {

    /**
     * 配置发送者，application-dev.yml中
     */
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 随机产生验证码
     * @return string验证码
     */
    public String setVerifyCode() {
        // 截取UUID前6位作为随机验证码
        return UUID.randomUUID().toString().substring(0,6).toUpperCase();
    }

    @Override
    public JsonResult<String> sendEmailVerify(String to, String title) {
        // 获取随机验证码
        String verifyCode = this.setVerifyCode();

        // 将验证码放入redis中
        ValueOperations<String,String> verifyCodeCache = stringRedisTemplate.opsForValue();
        verifyCodeCache.set(to,verifyCode);
        // 向目标邮箱发送验证码
        System.out.println(verifyCodeCache.get(to));
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(title);
        mailMessage.setText(verifyCode);
        mailSender.send(mailMessage);
        return ResultTool.success("发送成功");
    }
}
