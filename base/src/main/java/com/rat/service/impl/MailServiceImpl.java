package com.rat.service.impl;

import com.rat.cache.RedisUtil;
import com.rat.info.JsonResult;
import com.rat.info.ResultTool;
import com.rat.service.MailService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    /**
     * 模块名，方法名，参数常量
     */
    private static final String PREFIX = "outage";
    private static final String TOKEN = "token";
    private static final String VERIFY_CODE = "verifyCode";

    /**
     * 配置发送者，application-dev.yml中
     */
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 注入redis工具类
     */
    @Autowired
    private RedisUtil redisUtil;

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
        // 生成验证码对应的key
        String key = redisUtil.keyMaker(PREFIX,VERIFY_CODE,to);
        redisUtil.setCache(key,verifyCode,900);
        // 向目标邮箱发送验证码
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(title);
        mailMessage.setText(verifyCode);
        mailSender.send(mailMessage);
        // log
        log.info("邮箱:{},验证码:{}",to,verifyCode);
        return ResultTool.success("发送成功");
    }
}
