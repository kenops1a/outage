package com.rat.mapper;

import com.rat.model.MessageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/13 14:36
 */
@Mapper
public interface MessageMapper {

    /**
     * 获取用户消息列表
     * 可以以发送人id查询，以接收人id查询
     * @param sendId 发送者
     * @param receiveId 接收者
     * @return 消息列表
     */
    List<MessageModel> getMessageList(@Param("sendId") int sendId, @Param("receiveId") int receiveId);

    Integer addMessage(MessageModel messageModel);
}
