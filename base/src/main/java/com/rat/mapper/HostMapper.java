package com.rat.mapper;

import com.rat.model.HostModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/28 9:28
 */
@Mapper
public interface HostMapper {

    /**
     * 获取全部主持人
     * @return 主持人list
     */
    List<HostModel> getHostList();

    /**
     * 分类查询
     * @param type 类别
     * @return 主持人list
     */
    List<HostModel> getHostListByType(String type);

    /**
     * id查询
     * @param hostId 主持人id
     * @return 主持人对象
     */
    HostModel getHostById(@Param("userId") int hostId);

    /**
     * 模糊查询
     * @param nameLike 模糊词
     * @return 主持人列表
     */
    List<HostModel> getHostListByNameLike(String nameLike);

    /**
     * 注册时间查询
     * @param date 日期
     * @return 主持人列表
     */
    List<HostModel> getHostListByTime(@Param("date") Date date);

    /**
     * 按性别查询
     * @param sex 性别
     * @return 主持人列表
     */
    List<HostModel> getHostListBySex(String sex);

    /**
     * 添加host表记录
     * @param hostModel 主持人信息对象，此对象的create_by为hm_user_role中对应的user_id
     * @return Integer
     */
    Integer insertHost(HostModel hostModel);

    /**
     * 添加user_role记录
     * @param userId 用户id
     * @param roleId 权限id
     * @return Integer
     */
    Integer insertUserRole(int userId, int roleId);

    /**
     * 修改主持人信息
     * @param hostModel 主持人信息对象
     * @return Integer
     */
    Integer updateHostInfo(HostModel hostModel);

    /**
     * 通过id移除主持人信息记录
     * @param userId 用户id
     * @return Integer
     */
    Integer deleteHostById(int userId);

    /**
     * 通过id移除用户权限
     * @param userId 用户id
     * @param roleId 权限id
     * @return Integer
     */
    Integer removeUserRole(int userId, int roleId);

}
