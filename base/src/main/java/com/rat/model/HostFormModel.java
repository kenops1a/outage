package com.rat.model;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/13 14:23
 */
public class HostFormModel extends BaseModel{
    private int id;
    @NotNull
    private String address;
    @NotNull
    private String type;
    @NotNull
    private Date date;
    private int hostId;
    private String hostName;
    private String nick;
    @NotNull
    private double money;
    private String status;
    private int createBy;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public HostFormModel(int createBy, Date createTime, int updateBy, Date updateTime, String remark, int id, @NotNull String address, @NotNull String type, @NotNull Date date, int hostId, String hostName, String nick, @NotNull double money, String status, int createBy1, Date createTime1) {
        super(createBy, createTime, updateBy, updateTime, remark);
        this.id = id;
        this.address = address;
        this.type = type;
        this.date = date;
        this.hostId = hostId;
        this.hostName = hostName;
        this.nick = nick;
        this.money = money;
        this.status = status;
        this.createBy = createBy1;
        this.createTime = createTime1;
    }

    public HostFormModel() {
    }
}
