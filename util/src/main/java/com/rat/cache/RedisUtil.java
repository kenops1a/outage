package com.rat.cache;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/21 9:30
 */
public interface RedisUtil {

    /**
     * 通过key获取value
     *
     * @param key 需要查询的key
     * @return value
     */
    String getCache(String key);

    /**
     * 删除key
     *
     * @param key 需要删除的key
     * @return int
     */
    Boolean deleteCache(String key);

    /**
     * 添加新key
     *
     * @param key   键
     * @param value 值
     * @param second 时长/秒
     */
    void setCache(String key, String value, int second);

    /**
     * 是否存在key
     * @param key 键
     * @return true/false
     */
    Boolean hasKey(String key);
}
