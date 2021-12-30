package com.rat.service;

import com.rat.model.HostModel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/28 9:28
 */
public interface HostService {

    /**
     * 获取全部主持人
     * @param page 起始页
     * @param pageSize 页面大小
     * @return 主持人list
     */
    List<HostModel> getHostList(Integer page, Integer pageSize);

    /**
     * 分类查询
     * @param type 类别
     * @return 主持人list
     */
    List<HostModel> getHostListByType(String type);

    /**
     * id查询
     * @param hostId 拥有主持人角色的用户id
     * @return 主持人对象
     */
    HostModel getHostById(int hostId);

    /**
     * 模糊查询
     * @param nameLike 模糊词
     * @return 主持人列表
     */
    List<HostModel> getHostListByNameLike(String nameLike);

    /**
     * 注册日期大于某个时间的主持人列表
     * @param timeMark 日期标识，1-一个月，3-三个月，6-六个月，12-十二个月
     * @return 主持人列表
     */
    List<HostModel> getHostListByTime(String timeMark);

    /**
     * 按性别查询
     * @param sex 性别
     * @return 主持人列表
     */
    List<HostModel> getHostListBySex(String sex);

    /**
     * 给用户添加主持人角色
     * @param hostModel 主持人对象
     * @param userId 用户id
     * @return Integer
     */
    Integer createHost(HostModel hostModel, int userId);

    /**
     * 更新主持人信息
     * @param hostModel 主持人实体类
     * @return Integer
     */
    Integer updateHostInfo(HostModel hostModel);

    /**
     * 通过Id删除主持人
     * @param hostId 拥有主持人角色的用户id
     * @return Integer
     */
    Integer deleteHostById(int hostId);

}
