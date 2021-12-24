package com.rat.service.impl;

import com.rat.mapper.MessageMapper;
import com.rat.model.MessageModel;
import com.rat.service.MessageService;
import com.rat.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/14 10:04
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    TokenService tokenService;

    @Override
    public List<MessageModel> getMessageList(int sendId, int receiveId) {
        return messageMapper.getMessageList(sendId, receiveId);
    }

    @Override
    public MessageModel getMessageById() {
        return null;
    }

    @Override
    public Integer addMessage(MessageModel messageModel) {
        return messageMapper.addMessage(messageModel);
    }

    @Override
    public int deleteMessageById(int id) {
        return 0;
    }
}
