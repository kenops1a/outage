package com.rat.service;

import com.rat.info.JsonResult;
import com.rat.model.RoleMenuModel;
import com.rat.model.UserModel;
import com.rat.model.UserRoleModel;
import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/10 16:13
 */
public interface UserService {

    /**
     * 获取全部用户
     * @return 用户对象集合
     */
    JsonResult<List<UserModel>> getUserList();

    /**
     * 通过邮箱账号获取用户
     * @param userModel 需要参数的属性：email
     * @return user
     */
    JsonResult<List<UserModel>> getUserByEmail(UserModel userModel);

    /**
     * 通过手机号查询用户
     * @param userModel 需要参数的属性：phone
     * @return user
     */
   JsonResult<UserModel> getUserByPhone(UserModel userModel);

    /**
     * 通过邮箱账号回去用户角色
     * @param email 邮箱账号
     * @return userRoleModel
     */
    JsonResult<UserRoleModel> getUserRoleByEmail(String email);

    /**
     * 校验用户账号密码是否正确
     * @param userModel 携带参数的对象
     * @return int
     */
    JsonResult<Integer> checkUserItem(UserModel userModel);

    /**
     * 获取用户权限
     * @param email 用户邮箱账号
     * @return 权限列表
     */
    JsonResult<List<RoleMenuModel>> getUserRoleMenu(String email);

    /**
     * 修改用户信息
     * @param userModel 修改的用户对象
     * @return Integer
     */
    JsonResult<Integer> updateUser(UserModel userModel);

    /**
     * 添加用户
     * @param userModel 用户对象
     * @return Integer
     */
    JsonResult<Integer> addUser(UserModel userModel);

    /**
     * 删除用户
     * @param email 用户邮箱账号
     * @return Integer
     */
    JsonResult<Integer> deleteUser(String email);

    /**
     * 修改用户密码
     * @param userId 用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return Integer
     */
    JsonResult<Integer> updatePassword(String oldPassword, String newPassword, int userId);

    /**
     * 修改用户昵称
     * @param userModel 用户对象
     * @return Integer
     */
    JsonResult<Integer> updateNickname(UserModel userModel);

    JsonResult<Integer> updateName(String name, int id);

    /**
     * 获取用户状态
     * @param email 邮箱账号
     * @return JsonResult
     */
    JsonResult<String> getUserStatus(String email);

    /**
     * 冻结用账号
     * @param userId 需要修改的用户状态
     * @return Integer
     */
    JsonResult<Integer> updateUserStatus(int userId, int lockOrUnLock);
}
