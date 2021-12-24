package com.rat.controller;

import com.rat.info.JsonResult;
import com.rat.info.ResultCode;
import com.rat.info.ResultTool;
import com.rat.model.MessageModel;
import com.rat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/13 14:35
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    /**
     * 获取用户消息列表
     * @param sendId 发送者
     * @param receiveId 接收者
     * @return JsonResult<List<MessageModel>>
     */
    @RequestMapping(value = "getMessageList", method = RequestMethod.GET)
    public JsonResult<List<MessageModel>> getMessageList(@RequestParam int sendId, @RequestParam int receiveId) {

        // 校验参数完整性
        if (sendId == 0 || receiveId == 0) {
            return ResultTool.faild(ResultCode.PARAM_IS_REQUIRED);
        }

        return ResultTool.success(messageService.getMessageList(sendId, receiveId));
    }

    /**
     * 发送消息
     * @param messageModel 需要赋值的属性：userId, createBy, message
     * @return JsonResult<Integer>
     */
    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public JsonResult<Integer> sendMessage(@RequestBody MessageModel messageModel) {

        // 校验参数完整性
        if (messageModel.getUserId() == 0 || messageModel.getCreateBy() == 0) {
            return ResultTool.faild(ResultCode.PARAM_IS_REQUIRED);
        }
        // 补全消息对象属性值
        messageModel.setCreateTime(new Date());
        messageModel.setStatus("0");
        return ResultTool.success(messageService.addMessage(messageModel));
    }
}
