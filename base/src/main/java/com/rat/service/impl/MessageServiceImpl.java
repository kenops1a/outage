package com.rat.service.impl;

import com.rat.mapper.MessageMapper;
import com.rat.model.MessageModel;
import com.rat.service.MessageService;
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

    @Override
    public List<MessageModel> getMessageListById() {
        return null;
    }

    @Override
    public MessageModel getMessageById() {
        return null;
    }

    @Override
    public int addMessage(MessageModel messageModel) {
        return 0;
    }

    @Override
    public int deleteMessageById(int id) {
        return 0;
    }
}
