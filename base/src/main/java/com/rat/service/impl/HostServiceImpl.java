package com.rat.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rat.mapper.HostMapper;
import com.rat.mapper.UserMapper;
import com.rat.model.HostModel;
import com.rat.service.HostService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/28 9:28
 */
@Service
public class HostServiceImpl implements HostService {

    /**
     * 时长
     */
    private static final String MOUNTH_ONE = "1";
    private static final String MOUNTH_THREE = "3";
    private static final String MOUNTH_SIX = "6";
    private static final String YEAR_ONE = "12";
    /**
     * 权限
     * SIMPLE_ROLE: 普通用户角色
     * HOST_ROLE: 主持人角色
     * ADMIN_ROLE: 管理员角色
     */
    private static final int SIMPLE_ROLE = 1;
    private static final int HOST_ROLE = 2;
    private static final int ADMIN_ROLE = 3;


    @Autowired
    private HostMapper hostMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<HostModel> getHostList(Integer page, Integer pageSize) {
        // 分页起始码，每页页数
        PageHelper.startPage(page, pageSize);
        // 获取用户列表
        List<HostModel> hosts = hostMapper.getHostList();
        PageInfo<HostModel> pageInfo = new PageInfo<>(hosts);
        // 返回存在并且用户状态为‘1’（正常）的主持人列表
        return pageInfo.getList();
    }

    @Override
    public List<HostModel> getHostListByType(String type) {
        return hostMapper.getHostListByType(type);
    }

    @Override
    public HostModel getHostById(int hostId) {
        return hostMapper.getHostById(hostId);
    }

    @Override
    public List<HostModel> getHostListByNameLike(String nameLike) {
        return hostMapper.getHostListByNameLike(nameLike);
    }

    @Override
    public List<HostModel> getHostListByTime(String timeMark) {
        // 得到一个Calendar的实例
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        switch (timeMark) {
            case MOUNTH_ONE:
                calendar.add(Calendar.MONTH, -1);
                System.out.println(calendar.getTime());
                return hostMapper.getHostListByTime(calendar.getTime());
            case MOUNTH_THREE:
                calendar.add(Calendar.MONTH, -3);
                return hostMapper.getHostListByTime(calendar.getTime());
            case MOUNTH_SIX:
                calendar.add(Calendar.MONTH, -6);
                return hostMapper.getHostListByTime(calendar.getTime());
            case YEAR_ONE:
                calendar.add(Calendar.YEAR, -1);
                return hostMapper.getHostListByTime(calendar.getTime());
            default:
                System.out.println("参数错误");
        }
        return null;
    }

    @Override
    public List<HostModel> getHostListBySex(String sex) {
        return hostMapper.getHostListBySex(sex);
    }

    /**
     * 添加spring事务管理
     * 新增主持人需要两步操作，1-向user_role中添加记录，2-向host中添加记录
     * @param hostModel 主持人对象
     * @param userId 用户id
     * @return Integer
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer createHost(HostModel hostModel, int userId) {
        // 判断用户是否存在或用户状态是否正常
        if (userMapper.getUserById(userId) == null || !"1".equals(userMapper.getUserById(userId).getStatus())) {
            return 0;
        }
        // 判断是否存在userId为userId，roleId为HOST_ROLE的记录
        if (hostMapper.getUserRole(userId, HOST_ROLE) != null) {
            return 0;
        }
        // 判断主持人信息表中是否存在记录
        if (hostMapper.getHostById(userId) != null) {
            return 0;
        }
        // 给userId对应的用户添加主持人角色
        hostMapper.insertUserRole(userId, HOST_ROLE);
        // 补充hostModel信息
        hostModel.setCreateBy(userId);
        hostModel.setCreateTime(new Date());
        // 向数据库中添加主持人信息
        return hostMapper.insertHost(hostModel);
    }

    @Override
    public Integer updateHostInfo(HostModel hostModel) {
        return hostMapper.updateHostInfo(hostModel);
    }

    /**
     * 添加事务管理注解@Transactional
     * @param userId 用户id
     * @return Integer
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteHostById(int userId) {
        int hostDel, roleDel;
        // 状态为已删除和被冻结的账号无法再删除
        if (!"1".equals(userMapper.getUserById(userId).getStatus())) {
            return 0;
        }
        // 删除hm_host中的记录(主持人信息表)
        hostDel = hostMapper.deleteHostById(userId);
        // 删除hm_user_role中对应的记录(用户-角色表)
        roleDel = hostMapper.removeUserRole(userId, HOST_ROLE);
        return 1;
    }
}
