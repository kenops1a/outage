package com.rat.controller;

import com.rat.info.JsonResult;
import com.rat.info.ResultCode;
import com.rat.info.ResultTool;
import com.rat.model.RoleMenuModel;
import com.rat.model.UserModel;
import com.rat.model.UserRoleModel;
import com.rat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/13 13:52
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Qualifier("userServiceImpl")
    @Autowired
    UserService userService;

    /**
     * 获取全部用户
     * @return UserModel对象集合
     */
    @RequestMapping("/getUserList")
    public JsonResult<List<UserModel>> getUserList(@RequestParam int page, @RequestParam int pageSize) {
        return userService.getUserList(page, pageSize);
    }

    /**
     * 通过email获取用户
     * @param userModel 需要参数的属性：email
     */
    @RequestMapping(value = "/getUserByEmail", method = RequestMethod.POST)
    public JsonResult<List<UserModel>> getUserByEmail(@RequestBody UserModel userModel) {
        /*
         测试数据
         */
        // 校验参数完整性
        if ("".equals(userModel.getEmail())) {
            return ResultTool.failed(ResultCode.PARAM_IS_REQUIRED);
        }
        return userService.getUserByEmail(userModel);
    }

    /**
     * 账号密码校验
     * @param userModel 需要参数的属性：email，password
     * @return JsonResult
     */
    @PostMapping("/checkUserItem")
    public JsonResult<Integer> checkUserItem(@RequestBody UserModel userModel) {
        /*
         测试数据
         */
        userModel.setPassword("helloworld");
        userModel.setEmail("xx");
        return userService.checkUserItem(userModel);
    }

    /**
     * 通过email获取对应用户的菜单列表
     * @param map 用户邮箱键值对
     * @return 菜单列表
     */
    @GetMapping("/getUserRoleMenu")
    public JsonResult<List<RoleMenuModel>> getUserRoleMenu(@RequestBody Map<String,String> map) {
        // 检查userModel中的email是否为空
//        if ("".equals(email)) {
//            return ResultTool.faild(ResultCode.PARAM_IS_BLANK);
//            // 数据库中是否存在email对应的记录
//        }
        System.out.println(userService.getUserRoleMenu(map.get("email")));
        return userService.getUserRoleMenu(map.get("email"));
    }

    /**
     * 通过email获取用户角色
     * @return UserRoleModel
     */
    @GetMapping("/getUserRoleByEmail")
    public JsonResult<UserRoleModel> getUserRoleByEmail(@RequestBody Map<String,String> map) {
        return userService.getUserRoleByEmail(map.get("email"));
    }

    /**
     * 添加用户
     * @param userModel 用户对象
     * @return Integer
     */
    @PostMapping("/addUser")
    public JsonResult<Integer> addUser(@RequestBody @Valid UserModel userModel) {
        // 测试数据
//        userModel.setNick("神奇海螺");
//        userModel.setEmail("10086@qq.com");
//        userModel.setPassword("helloworld");
        System.out.println(userModel);
        return userService.addUser(userModel);
    }

    /**
     * 通过用户邮箱账号删除用户记录
     * @param map 存放用户邮箱键值对的map
     * @return Integer
     */
    @PostMapping("/deleteUserByEmail")
    public JsonResult<Integer> deleteUserById(@RequestBody Map<String, String> map) {
        String email = map.get("email");
        if ("".equals(email) || email == null) {
            return ResultTool.failed(ResultCode.PARAM_IS_BLANK);
        }
        return userService.deleteUser(email);
    }

    @PostMapping("/testJson")
    public Map<String,String> testJson(@RequestBody Map<String, String> map) {
        // map可以获取json中的属性和属性值
        // 在装箱的时候自动调用的是Integer的valueOf(int)方法。
        // 而在拆箱的时候自动调用的是Integer的intValue方法。
        System.out.println(map.get("email"));
        System.out.println(map);
        return map;
    }
    @PostMapping("/getJson")
    public String getJson(@RequestBody String email) {
        System.out.println(email);
        return email;
    }

    /**
     * 修改密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param userId 用户id
     * @return Integer
     */
    @PostMapping("/updatePassword")
    public JsonResult<Integer> updatePassword(@RequestParam String oldPassword,@RequestParam String newPassword,@RequestParam int userId) {
        if (oldPassword == null || newPassword == null) {
            return ResultTool.failed(ResultCode.PARAM_IS_BLANK);
        } else {
            return userService.updatePassword(oldPassword,newPassword,userId);
        }
    }

    /**
     * 修改用户信息
     * @param userModel 必要参数email，其他可有可无
     * @return Integer
     */
    @PostMapping("/updateUser")
    public JsonResult<Integer> updateNickname(@RequestBody UserModel userModel) {
        if ("".equals(userModel.getEmail())) {
            return ResultTool.failed(ResultCode.PARAM_IS_REQUIRED);
        }
        if (userService.getUserByEmail(userModel) == null) {
            return ResultTool.failed(ResultCode.USER_ACCOUNT_NOT_EXIST);
        } else if (userService.checkUserItem(userModel) != null) {
            return ResultTool.failed(ResultCode.NO_PERMISSION);
        }
        return userService.updateUser(userModel);
    }

    /**
     * 冻结用户账号
     * @param userId 用户id
     * @param lockOrUnLock 冻结解锁标识，0-冻结，1-解冻
     * @return Integer
     */
    @PostMapping("/userAndLock")
    public JsonResult<Integer> userLock(@RequestParam int userId, int lockOrUnLock) {
        // 校验参数完整
        if (userId == 0) {
            return ResultTool.failed(ResultCode.PARAM_IS_REQUIRED);
        }
        return userService.updateUserStatus(userId, lockOrUnLock);
    }
}
