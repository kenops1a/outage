package com.rat.web.websocket.dto.output;

import lombok.Data;

import java.util.Date;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/24 13:58
 */
@Data
public class MsgVo {

    private Integer userId;
    private Integer toId;
    private Date createTime;
    private String status;
    private String message;

}
