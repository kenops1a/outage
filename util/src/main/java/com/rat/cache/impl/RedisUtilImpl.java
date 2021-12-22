package com.rat.cache.impl;

import com.rat.cache.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/21 9:37
 */
@Service
public class RedisUtilImpl implements RedisUtil {

    /**
     * KEY_PREFIX:主数据库系统标识
     * KEY_SPLIT_CHAR分割字符，默认[:]，使用：可用于rdm分组查看
     */
    public static final String KEY_PREFIX = "outage";
    private static final String KEY_SPLIT_CHAR = ":";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getCache(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean deleteCache(String key) {
        return stringRedisTemplate.delete(key);
    }

    @Override
    public void setCache(String key, String value, int second) {
        stringRedisTemplate.opsForValue().set(key, value, second, TimeUnit.SECONDS);
    }


    @Override
    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public String keyMaker(String prefix, String... args) {
        // 项目前缀
        if (prefix == null) {
            prefix = KEY_PREFIX;
        }
        StringBuilder key = new StringBuilder(prefix);
        // KEY_SPLIT_CHAR 为分割字符
        for (String arg : args) {
            key.append(KEY_SPLIT_CHAR).append(arg);
        }
        return key.toString();
    }
}
