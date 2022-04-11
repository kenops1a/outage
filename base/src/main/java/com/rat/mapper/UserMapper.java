package com.rat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rat.info.JsonResult;
import com.rat.model.*;
import org.apache.catalina.Host;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.parameters.P;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/10 13:55
 */
@Mapper
public interface UserMapper extends BaseMapper<HostModel> {

    List<UserModel> getUserList();

    /**
     * 通过参数查询用户记录
     * @param userModel 带有属性值的用户对象
     * @return List<UserModel>
     */
    List<UserModel> getUserListByItem(UserModel userModel);


    /**
     * 通过昵称模糊查询
     * @param nick
     * @return
     */
    List<UserModel> getUserByNick(@Param("nick") String nick);

    /**
     * 通过邮箱账号获取用户
     * @param email 邮箱账号
     * @return user
     */
    UserModel getUserByEmail(String email);

    /**
     * 手机号
     * @param phone 手机号
     * @return UserModel
     */
    UserModel getUserByPhone(String phone);

    /**
     * 获取用户角色
     * @param email 邮箱账号
     * @return userRoleModel
     */
    UserRoleModel getUserRoleByEmail(String email);

    /**
     * 通过用户id获取用户对象
     * @param id 用户id
     * @return userModel
     */
    UserModel getUserById(int id);
    /**
     * 获取用户权限列表
     * @param email 邮箱账号
     * @return List<RoleMenuModel>
     */
    List<RoleMenuModel> getUserRoleMenu(String email);

    /**
     * 修改用户信息
     * @param userModel userModel
     * @return Integer
     */
    Integer updateUser(UserModel userModel);

    /**
     * 添加用户
     * @param userModel 用户对象
     * @return Integer
     */
    Integer addUser(UserModel userModel);

    /**
     * 删除用户
     * @param email 用户邮箱
     * @param status 需要修改为什么状态
     * @return Integer
     */
    Integer updateUserStatusByEmail(@Param("email") String email,@Param("status") String status);

    /**
     * 上传文件
     *
     * @param fileModel 文件实体
     * @return 0 : 1
     */
    Integer upload(@Param("file") FileModel fileModel);

    /**
     * 上传图片
     *
     * @param: imgModel
     * @return: java.lang.Integer
     */
    Integer uploadImg(@Param("img") ImgModel imgModel);

    /**
     * 提交认证申请
     *
     * @param: hostModel
     * @return: java.lang.Integer
     */
    Integer hostAsset(@Param("host") HostModel hostModel) throws DuplicateKeyException;

    /**
     * 获取认证记录
     *
     * @param: id
     * @return: com.rat.model.HostModel
     */
    HostModel getAssetRecord(@Param("id") int id);

    /**
     * @param: hostModel
     * @return: java.lang.Boolean
     */
    Boolean updateAssetRecord(@Param("host") HostModel hostModel);

    /**
     * @param:
     * @return: java.util.List<com.rat.model.HostModel>
     */
    List<HostModel> getAssetRecordList();

    /**
     * 获取认证文件
     *
     * @param id
     * @return
     */
    FileModel getAssetFile(@Param("id") int id);

    /**
     * 撤回申请
     *
     * @param: id
     * @return: java.lang.Integer
     */
    Integer delAssetRecord(@Param("id") int id);

    /**
     * 删除申请文件
     *
     * @param: id
     * @return: java.lang.Integer
     */
    Integer delAssetFile(@Param("id") int id);

}
