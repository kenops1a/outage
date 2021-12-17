package com.rat.controller;

import com.rat.info.JsonResult;
import com.rat.info.ResultCode;
import com.rat.info.ResultTool;
import com.rat.model.HostFormModel;
import com.rat.service.HostFormService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/13 16:02
 */
@RestController
public class HostFormController {


    @Autowired
    @Qualifier("hostFormServiceImpl")
    HostFormService hostFormService;

    /**
     * 获取指定id的form表单记录
     * @return
     */
    @RequestMapping(value = "/getFormById", method = RequestMethod.GET)
    public JsonResult<HostFormModel> getFormById(@RequestParam int formId) {
        return hostFormService.getFormById(formId);
    }

    /**
     * 获取对应用户id的对应状态订单信息
     * @param hostFormModel 属性：status，createBy
     * @return 返回封装在JsonResult中的订单对象集合
     */
    @RequestMapping(value = "/getListByItem", method = RequestMethod.POST)
    public JsonResult<List<HostFormModel>> getHostFormList(@RequestBody HostFormModel hostFormModel) {
        /*
         * 测试参数
         */
        hostFormModel.setStatus("1");
        hostFormModel.setCreateBy(2);
        return hostFormService.getFormListByItem(hostFormModel);
    }

    /**
     * 获取对应用户id的订单列表
     * @param hostFormModel 需要参数的属性：createBy
     * @return 返回封装在JsonResult中的订单对象集合
     */
    @RequestMapping(value = "/getListByUserId", method = RequestMethod.POST)
    public JsonResult<List<HostFormModel>> getHostFormListByUserId(@RequestBody HostFormModel hostFormModel) {
        return hostFormService.getFormListByItem(hostFormModel);
    }

    /**
     * 获取对应主持人id的订单列表
     * @param hostFormModel 需要参数的属性：hostId
     * @return 返回封装在JsonResult中的订单对象集合
     */
    @RequestMapping(value = "/getListByHostId", method = RequestMethod.POST)
    public JsonResult<List<HostFormModel>> getHostFormListByHostId(@RequestBody HostFormModel hostFormModel) {
        return hostFormService.getFormListByItem(hostFormModel);
    }

    /**
     * 添加订单，
     * @param hostFormModel 表单对象
     * @param userId 用户id
     * @param hostId 主持人id
     * @return JsonResult
     */
    @RequestMapping(value = "/addForm", method = RequestMethod.POST)
    public JsonResult<Integer> addHostForm(@RequestBody HostFormModel hostFormModel, @RequestParam int userId, @RequestParam int hostId) {
        return hostFormService.addForm(hostFormModel,userId,hostId);
    }

    /**
     * 删除指定id表单记录
     * @param formId 表单id
     * @param userId 用户id
     * @return JsonResult
     */
    @RequestMapping(value = "/deleteFormById", method = RequestMethod.GET)
    public JsonResult<Integer> deleteFormById(@RequestParam int formId, @RequestParam int userId) {
        return hostFormService.deleteFormById(formId, userId);
    }

    /**
     * 批量删除表单记录
     * @param ids 表单id集合，存入需要批量删除的表单id
     * @return JsonResult
     */
    @RequestMapping(value = "/deleteFormByIds", method = RequestMethod.POST)
    public JsonResult<Integer> deleteFormByIds(@RequestBody int[] ids) {
        /*
        测试参数
         */
        if (ids == null || ids.length == 0) {
            return ResultTool.faild(ResultCode.PARAM_IS_BLANK);
        }
        return hostFormService.deleteFormByIds(ids);
    }

    /**
     * 修改表单日期
     * @param hostFormModel 需要值的属性：date
     */
    @RequestMapping(value = "/updateFormDate", method = RequestMethod.POST)
    public JsonResult<Integer> updateForm(@Valid @RequestBody HostFormModel hostFormModel) {
        return hostFormService.updateForm(hostFormModel);
    }

}
