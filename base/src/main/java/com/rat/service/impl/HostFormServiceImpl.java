package com.rat.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rat.info.JsonResult;
import com.rat.info.ResultCode;
import com.rat.info.ResultTool;
import com.rat.mapper.HostFormMapper;
import com.rat.mapper.UserMapper;
import com.rat.model.HostFormModel;
import com.rat.model.UserModel;
import com.rat.service.HostFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/13 14:35
 */
@Service
public class HostFormServiceImpl implements HostFormService {
    private final String STATUS_FINISH = "1";
    private final String STATUS_FAILD = "0";
    private final String STATUS_HAVING = "2";
    private final String STATUS_DELETE = "3";

    @Autowired
    HostFormMapper hostFormMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public JsonResult<Integer> addForm(HostFormModel hostFormModel, int userId, int hostId) {
        UserModel userModel = userMapper.getUserById(hostId);
        /*
         * 将新增表单状态设为进行中
         * 创建者id设为用户id
         * 日期设为当前日期
         * 主持人id设为hostId
         * hostName设为获取的主持人姓名
         * nick设为获取的主持人昵称
         */
        hostFormModel.setStatus("2");
        hostFormModel.setCreateBy(userId);
        hostFormModel.setCreateTime(new Date());
        hostFormModel.setHostId(hostId);
        hostFormModel.setHostName(userModel.getName());
        hostFormModel.setNick(userModel.getNick());
        return ResultTool.success(hostFormMapper.addForm(hostFormModel));
    }

    @Override
    public JsonResult<Integer> updateForm(HostFormModel hostFormModel) {
        /*
            缺少用户权限验证，判断用户是否有权限修改订单
            只有角色为主持人，管理员的用户才能修改订单
            只有状态为进行中的订单才可以修改
        */
        int index = hostFormMapper.updateForm(hostFormModel);
        return index <= 0 ? ResultTool.failed(ResultCode.ITEM_NOT_EXIST):ResultTool.success(index);
    }

    @Override
    public JsonResult<Integer> deleteFormById(int formId, int userId) {
        /*
            缺少用户权限验证，判断用户是否有权限删除此id的表单，未实现
            角色为管理员，或者用户id与要删除的表单id相同的用户，可以删除对应订单记录
        */

        HostFormModel hostFormModel = hostFormMapper.getFormById(formId);
        // 判断是否存在记录，不存在返回错误提示
        if (hostFormModel == null) {
            return ResultTool.failed(ResultCode.ITEM_NOT_EXIST);
            // 判断订单状态，只有状态不为 “2” 的订单才可进行删除操作
        } else if (STATUS_HAVING.equals(hostFormModel.getStatus())) {
            return ResultTool.failed(ResultCode.ITEM_CAN_NOT_ALTER);
        } else {
            // 调用hostFormMapper将表单status设为STATUS_DELETE
            return ResultTool.success(hostFormMapper.updateStatusById(formId,STATUS_DELETE));
        }
    }

    @Override
    public JsonResult<Integer> deleteFormByIds(int[] ids) {
        // 当指定id的记录存在，并且状态不为 “2” 进行中时，将id添加入新的list集合中
        List<Integer> idsList = new ArrayList<>();
        for (int id : ids) {
            HostFormModel hostFormModel = hostFormMapper.getFormById(id);
            if (hostFormModel != null && !STATUS_HAVING.equals(hostFormModel.getStatus())) {
                idsList.add(id);
            }
        }
        if (idsList.isEmpty()) {
            return ResultTool.failed(ResultCode.ITEM_CAN_NOT_ALTER);
        } else {
            return ResultTool.success(hostFormMapper.updateStatusByIds(idsList, STATUS_DELETE));
        }
    }

    @Override
    public JsonResult<HostFormModel> getFormById(int formId) {
        if (formId == 0) {
            return ResultTool.failed(ResultCode.PARAM_IS_BLANK);
        } else {
            return ResultTool.success(hostFormMapper.getFormById(formId));
        }
    }

    @Override
    public Boolean checkFormStatus(int formId) {
        // 获取表单状态
        String status = hostFormMapper.getFormById(formId).getStatus();
        // 判断表单是否可以修改
        return STATUS_HAVING.equals(status);
    }

    @Override
    public JsonResult<List<HostFormModel>> getFormListByItem(HostFormModel hostFormModel, Integer page, Integer pageSize) {
        /*
         * 不能查询status状态为已删除的表单记录
         */
        if (STATUS_DELETE.equals(hostFormModel.getStatus())) {
            return ResultTool.failed(ResultCode.PARAM_NOT_VALID);
        }
        if (hostFormMapper.getFormListByItem(hostFormModel).isEmpty()) {
            return ResultTool.failed(ResultCode.ITEM_NOT_EXIST);
        }
        // 添加分页
        PageHelper.startPage(page, pageSize);
        List<HostFormModel> list = hostFormMapper.getFormListByItem(hostFormModel);
        PageInfo<HostFormModel> pageInfo = new PageInfo<>(list);

        return ResultTool.success(pageInfo.getList());
    }
}
