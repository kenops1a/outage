package com.rat.service;

import com.rat.model.MessageModel;

import java.util.List;

/**
 * @type: bug
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/13 14:34
 */
public interface MessageService {

    /**
     * 获取用户消息列表
     * 可以以发送人id查询，以接收人id查询
     * @param sendId 发送者
     * @param receiveId 接收者
     * @return 消息列表
     */
    List<MessageModel> getMessageList(int sendId, int receiveId);

    /**
     * 通过消息编号查询
     * @return null
     */
    MessageModel getMessageById();

    /**
     * 接收消息内容，存入数据库
     * @param messageModel 消息对象
     * @return null
     */
    Integer addMessage(MessageModel messageModel);

    /**
     * 通过编号删除消息记录
     * 将数据库status置为2，表示已删除
     */
    int deleteMessageById(int id);
}
