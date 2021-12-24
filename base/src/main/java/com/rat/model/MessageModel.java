package com.rat.model;

import java.util.Date;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/13 14:21
 */
public class MessageModel extends BaseModel {

    private int id;
    private int userId;
    private String message;
    private String status;

    @Override
    public int getCreateBy() {
        return super.getCreateBy();
    }

    @Override
    public void setCreateBy(int createBy) {
        super.setCreateBy(createBy);
    }

    @Override
    public Date getCreateTime() {
        return super.getCreateTime();
    }

    @Override
    public void setCreateTime(Date createTime) {
        super.setCreateTime(createTime);
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageModel() {
    }

    public MessageModel(int createBy, Date createTime, int updateBy, Date updateTime, String remark, int id, int userId, String message, String status) {
        super(createBy, createTime, updateBy, updateTime, remark);
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
