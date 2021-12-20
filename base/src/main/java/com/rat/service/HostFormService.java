package com.rat.service;

import com.rat.info.JsonResult;
import com.rat.model.HostFormModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/13 14:34
 */
public interface HostFormService {

    /**
     * 通过参数查询订单
     * @param hostFormModel 前端传递的带有属性的hostFormModel对象
     * @return 存储HostFormModel的JsonResult对象
     */
    JsonResult<List<HostFormModel>> getFormListByItem(HostFormModel hostFormModel);

    /**
     * 新建订单
     * @param hostFormModel 订单对象
     * @param userId 表单创建者id
     * @param hostId 主持人id
     * @return
     */
    JsonResult<Integer> addForm(HostFormModel hostFormModel, int userId, int hostId);
    /**
     * 更新订单信息
     * @param hostFormModel 更新订单
     * @return
     */
    JsonResult<Integer> updateForm(HostFormModel hostFormModel);

    /**
     * 删除订单
     * @param formId 订单编号
     * @param userId 用户id
     * @return JsonResult
     */
    JsonResult<Integer> deleteFormById(int formId, int userId);

    /**
     * 批量删除订单
     * @param ids 订单编号集合
     * @return
     */
    JsonResult<Integer> deleteFormByIds(int[] ids);


    /**
     * 获取指定id的form表单
     * @return
     */
    JsonResult<HostFormModel> getFormById(int formId);
}
