package com.rat.controller;

import com.github.pagehelper.PageHelper;
import com.rat.info.JsonResult;
import com.rat.info.ResultCode;
import com.rat.info.ResultTool;
import com.rat.model.HostModel;
import com.rat.service.HostService;
import com.rat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/28 9:27
 */
@RestController
@RequestMapping("/host")
public class HostController {

    @Autowired
    private HostService hostService;

    /**
     * 获取主持人列表
     * @param page 分页起始码
     * @param pageSize 每页页数
     * @return 主持人list
     */
    @RequestMapping(value = "/getHostList", method = RequestMethod.GET)
    public JsonResult<List<HostModel>> getHostList(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return ResultTool.success(hostService.getHostList(page, pageSize));
    }

    /**
     * 通过类别获取主持人列表
     * @param type 类别
     * @return 主持人list
     */
    @RequestMapping(value = "/getHostListByType", method = RequestMethod.GET)
    public JsonResult<List<HostModel>> getHostListByType(@RequestParam String type, @RequestParam int page, @RequestParam int pageSize) {
        // 参数不为空
        if ("".equals(type)) {
            return ResultTool.faild(ResultCode.PARAM_IS_REQUIRED);
        }
        return ResultTool.success(hostService.getHostListByType(type, page, pageSize));
    }

    /**
     * 通过id获取主持人对象
     * @param hostId 主持人id
     * @return HostModel
     */
    @RequestMapping(value = "/getHostById", method = RequestMethod.GET)
    public JsonResult<HostModel> getHostById(@RequestParam int hostId) {
        // 参数不为空
        if (hostId == 0) {
            return ResultTool.faild(ResultCode.PARAM_IS_REQUIRED);
        }
        return ResultTool.success(hostService.getHostById(hostId));
    }

    /**
     * 通过主持人姓名模糊查询
     * @param nameLike 姓名
     * @return 主持人list
     */
    @RequestMapping(value = "/getHostListByNameLike", method = RequestMethod.GET)
    public JsonResult<List<HostModel>> getHostListByNameLike(@RequestParam String nameLike) {
        // 参数不为空
        if ("".equals(nameLike)) {
            return ResultTool.faild(ResultCode.PARAM_IS_REQUIRED);
        }
        return ResultTool.success(hostService.getHostListByNameLike(nameLike));
    }

    /**
     * 获取指定注册时长的主持人
     * @param timeMark 日期标识
     * @return 主持人list
     */
    @RequestMapping(value = "/getHostListByTime", method = RequestMethod.GET)
    public JsonResult<List<HostModel>> getHostListByTime(@RequestParam String timeMark, @RequestParam Integer page, @RequestParam Integer pageSize) {
        // 参数不为空
        if ("".equals(timeMark)) {
            return ResultTool.faild(ResultCode.PARAM_IS_REQUIRED);
        }
        return ResultTool.success(hostService.getHostListByTime(timeMark, page, pageSize));
    }

    /**
     * 获取指定性别的主持人
     * @param sex 性别
     * @return 主持人list
     */
    @RequestMapping(value = "/getHostListBySex", method = RequestMethod.GET)
    public JsonResult<List<HostModel>> getHostListBySex(@RequestParam String sex, @RequestParam Integer page, @RequestParam Integer pageSize) {
        // 参数不为空
        if ("".equals(sex)) {
            return ResultTool.faild(ResultCode.PARAM_IS_REQUIRED);
        }
        return ResultTool.success(hostService.getHostListBySex(sex, page, pageSize));
    }

    /**
     * 更新主持人信息
     * @param hostModel 主持人类
     * @return Integer
     */
    @RequestMapping(value = "/updateHostInfo", method = RequestMethod.POST)
    public JsonResult<Integer> updateHostInfo(@RequestBody HostModel hostModel) {
        return ResultTool.success(hostService.updateHostInfo(hostModel));
    }

    /**
     * 需要修改
     * 增加对应id用户的主持人权限
     * hostModel参数完整性由Valid注解实现
     * @param hostModel 主持人信息
     * @param userId 被授予权限的用户id
     * @return Integer
     */
    @RequestMapping(value = "/addHost", method = RequestMethod.POST)
    public JsonResult<Integer> addHost(@RequestBody HostModel hostModel, @RequestParam int userId) {
        // 校验参数完整性
        if (userId == 0 || hostModel.getType() == null || hostModel.getMoney() == 0) {
            return ResultTool.faild(ResultCode.PARAM_IS_REQUIRED);
        }
        return ResultTool.success(hostService.createHost(hostModel, userId));
    }

    /**
     * 移除对应id用户的主持人权限
     * @param userId 用户id
     * @return Integer
     */
    @RequestMapping(value = "/deleteHostById", method = RequestMethod.POST)
    public JsonResult<Integer> deleteHostById(int userId) {
        return ResultTool.success(hostService.deleteHostById(userId));
    }
}
