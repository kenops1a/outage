package com.rat.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @description:
 * @tip: write_bug
 * @author: yaominc
 * @date: 11/4/2022 下午2:56
 */
public interface UserRoleBindMapper {

    /**
     * 给用户添加权限
     *
     * @param: userId
     * @param: roleId
     * @return: java.lang.Integer
     */
    Integer addRecord(@Param("userId") int userId, @Param("roleId") int roleId);
}
