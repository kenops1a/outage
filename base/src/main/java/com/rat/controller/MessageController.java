package com.rat.controller;

import com.rat.model.MessageModel;
import com.rat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/13 14:35
 */
@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping("getMessageList")
    public List<MessageModel> getMessageList(@RequestParam int sendId, @RequestParam int receiveId) {
        return messageService.getMessageListById();
    }

}
