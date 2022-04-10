package com.rat.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rat.info.JsonResult;
import com.rat.info.ResultCode;
import com.rat.info.ResultTool;
import com.rat.mapper.UserMapper;
import com.rat.model.*;
import com.rat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/10 16:13
 */
@Service
public class UserServiceImpl implements UserService {

    private final String USER_STATUS_LOCKED = "0";
    private final String USER_STATUS_NORMAL = "1";
    private final String USER_STATUS_DELETE = "2";
    private final String ASSET_STATUS_APPLY = "0";
    private final String ASSET_STATUS_FINISH = "1";
    private final String ASSET_STATUS_FAILD = "2";

    @Autowired
    UserMapper userMapper;

    @Override
    public JsonResult<List<UserModel>> getUserList(int page, int pageSize) {
//        /*
//        如果list为空，则返回记录为空
//         */
//        if (userMapper.getUserList().isEmpty()) {
//            return ResultTool.faild(ResultCode.ITEM_NOT_EXIST);
//        } else {
//            return ResultTool.success(userMapper.getUserList());
//        }

        if (userMapper.getUserList().isEmpty()) {
            return ResultTool.failed(ResultCode.ITEM_NOT_EXIST);
        }
        PageHelper.startPage(page, pageSize);
        List<UserModel> list = userMapper.getUserList();
        PageInfo<UserModel> pageInfo = new PageInfo<>(list);
        return ResultTool.success(pageInfo.getList());
    }

    @Override
    public JsonResult<UserModel> getUserById(int userId) {
        // 校验参数完整性
        if (userId == 0) {
            return ResultTool.failed(ResultCode.PARAM_IS_REQUIRED);
        }
        // 返回用户对象
        return ResultTool.success(userMapper.getUserById(userId));
    }

    @Override
    public JsonResult<List<UserModel>> getUserByEmail(UserModel userModel) {
        // 检查userModel中的email是否为空
        if ("".equals(userModel.getEmail())) {
            return ResultTool.failed(ResultCode.PARAM_IS_BLANK);
            // 数据库中是否存在email对应的记录
        } else if (userMapper.getUserListByItem(userModel).isEmpty()) {
            return ResultTool.failed(ResultCode.ITEM_NOT_EXIST);
        } else {
            return ResultTool.success(userMapper.getUserListByItem(userModel));
        }
    }

    @Override
    public JsonResult<UserModel> getUserByPhone(UserModel userModel) {
        if (userMapper.getUserByEmail(userModel.getEmail()) == null) {
            return ResultTool.failed(ResultCode.USER_ACCOUNT_NOT_EXIST);
        } else {
            return ResultTool.success(userMapper.getUserByPhone(userModel.getPhone()));
        }
    }

    @Override
    public JsonResult<UserRoleModel> getUserRoleByEmail(String email) {
        // 检查userModel中的email是否为空
        if ("".equals(email)) {
            return ResultTool.failed(ResultCode.PARAM_IS_BLANK);
            // 数据库中是否存在email对应的记录
        } else if (userMapper.getUserByEmail(email) == null) {
            return ResultTool.failed(ResultCode.ITEM_NOT_EXIST);
        } else {
            return ResultTool.success(userMapper.getUserRoleByEmail(email));
        }
    }

    @Override
    public JsonResult<Integer> checkUserItem(UserModel userModel) {
        // 检查userModel中的email是否为空
        if ("".equals(userModel.getEmail())) {
            return ResultTool.failed(ResultCode.PARAM_IS_BLANK);
            // 数据库中是否存在email对应的记录
        } else if (userMapper.getUserByEmail(userModel.getEmail()) == null) {
            return ResultTool.failed(ResultCode.ITEM_NOT_EXIST);
            // 对应记录密码是否与前端传递的密码相同
        } else if (!userMapper.getUserByEmail(userModel.getEmail()).getPassword().equals(userModel.getPassword())){
            return ResultTool.failed(ResultCode.USER_PASSWORD_ERROR);
        }
        return ResultTool.success();
    }

    @Override
    public JsonResult<List<RoleMenuModel>> getUserRoleMenu(String email) {
        return ResultTool.success(userMapper.getUserRoleMenu(email));
    }

    @Override
    public JsonResult<Integer> updateUser(UserModel userModel) {
        return ResultTool.success(userMapper.updateUser(userModel));
    }

    @Override
    public JsonResult<Integer> addUser(UserModel userModel) {
        if (userMapper.getUserByEmail(userModel.getEmail()) != null) {
            return ResultTool.failed(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        } else {
            return ResultTool.success(userMapper.addUser(userModel));
        }
    }

    @Override
    public JsonResult<Integer> deleteUser(String email) {
        // 如果对应id用户记录不存在或者状态为已删除，则返回记录不存在
        if (userMapper.getUserByEmail(email) == null || USER_STATUS_DELETE.equals(userMapper.getUserByEmail(email).getStatus())) {
            return  ResultTool.failed(ResultCode.USER_ACCOUNT_NOT_EXIST);
        } else {
            return ResultTool.success(userMapper.updateUserStatusByEmail(email, USER_STATUS_DELETE));
        }
    }

    @Override
    public JsonResult<Integer> updatePassword(String oldPassword, String newPassword, int userId) {
        // 创建参数userModel
        UserModel userModel = new UserModel();
        userModel = userMapper.getUserById(userId);
        // 判断原密码是否正确
        if (userModel == null) {
            return ResultTool.failed(ResultCode.USER_ACCOUNT_NOT_EXIST);
        } else if (!userModel.getPassword().equals(oldPassword)) {
            return ResultTool.failed(ResultCode.USER_PASSWORD_ERROR);
        } else {
            // 将参数userModel密码设置为newPassword
            userModel.setPassword(newPassword);
            return ResultTool.success(userMapper.updateUser(userModel));
        }
    }

    @Override
    public JsonResult<Integer> updateNickname(UserModel userModel) {
        if (userMapper.getUserById(userModel.getId()) == null) {
            return ResultTool.failed(ResultCode.USER_ACCOUNT_NOT_EXIST);
        } else {
            return ResultTool.success(userMapper.updateUser(userModel));
        }
    }

    @Override
    public JsonResult<Integer> updateName(String name, int id) {
        return null;
    }

    @Override
    public JsonResult<String> getUserStatus(String email) {
        UserModel userModel = userMapper.getUserByEmail(email);
        if (userModel == null || userModel.getStatus().equals(USER_STATUS_DELETE)) {
            // 用户记录为空或状态为已删除，则返回用户不存在
            return ResultTool.failed(ResultCode.USER_ACCOUNT_NOT_EXIST);
        } else if (userModel.getStatus().equals(USER_STATUS_LOCKED)) {
            // 用户状态为冻结则返回账号已冻结
            return ResultTool.failed(ResultCode.USER_ACCOUNT_LOCKED);
        } else {
            return ResultTool.success(userModel.getStatus());
        }
    }

    @Override
    public JsonResult<Integer> updateUserStatus(int userId, int lockOrUnLock) {
        UserModel userModel = userMapper.getUserById(userId);
        // 用户不存在
        if (userModel == null || USER_STATUS_DELETE.equals(userModel.getStatus())) {
            return ResultTool.failed(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        // 用户已被冻结且传递参数为1（解锁）
        if (lockOrUnLock == 1 && USER_STATUS_LOCKED.equals(userModel.getStatus())) {
            // 将用户状态设置为正常
            return ResultTool.success(userMapper.updateUserStatusByEmail(userModel.getEmail(), USER_STATUS_NORMAL));
        }
        if (lockOrUnLock == 0) {
            // 将用户状态设置为已冻结，返回操作结果
            return ResultTool.success(userMapper.updateUserStatusByEmail(userModel.getEmail(), USER_STATUS_LOCKED));
        }
        // 不符合上述情况则返回默认失败
        return ResultTool.failed();
    }

    @Override
    public Boolean hostAsset(int userId, String type) {
        HostModel hostModel = new HostModel();
        // 主持人状态默认为申请中
        hostModel.setStatus(ASSET_STATUS_APPLY);
        hostModel.setType(type);
        hostModel.setCreateBy(userId);
        hostModel.setCreateTime(new Date());
        int label = 0;
        try {
            label = userMapper.hostAsset(hostModel);
        } catch (DuplicateKeyException throwables) {
            throw new BaseExceptionModel("该用户已申请主持人认证");
        }
        return label > 0;
    }

    @Override
    public Boolean upload(FileModel fileModel) {
        /*  类型限制  */

        MultipartFile file = fileModel.getFile();
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String suffix = fileName.substring(fileName.indexOf("."));

        String httpName = UUID.randomUUID().toString() + suffix;

        /*  本地文件路径  */
        String fileFolder = "C:\\Users\\Kenopsia\\Desktop\\job\\project\\HostManager\\outage\\fileFolder";
        File folder = new File(fileFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        /*  网络相对路径  */
        String httpPath = "/file/" + httpName;

        try {
            file.transferTo(new File(fileFolder, httpName));
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败");
        }

        fileModel.setFileName(fileName);
        fileModel.setHttpPath(httpPath);

        Integer upload = userMapper.upload(fileModel);

        return upload > 0;
    }

    @Override
    public JsonResult<FileModel> getAssetFile(int id) {
        return ResultTool.success(userMapper.getAssetFile(id));
    }

    @Override
    public JsonResult<HostModel> getAssetRecord(int id) {
        return ResultTool.success(userMapper.getAssetRecord(id));
    }

    @Override
    public Boolean delAsset(int id) {
        int label1 = userMapper.delAssetFile(id);
        int label2 = userMapper.delAssetRecord(id);
        return label1 > 0 && label2 > 0;
    }


}
