package com.rat.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/13 13:54
 */
public class HostModel extends UserModel {
    private int hostId;
    @NotNull
    @TableField(value = "type")
    private String type;
    @NotNull
    private double money;
    private String status;
    @TableField(value = "create_by")
    private int createBy;
    @TableField(value = "create_time")
    private Date createTime;

    @Override
    public String toString() {
        return "HostModel{" +
                "hostId=" + hostId +
                ", type='" + type + '\'' +
                ", money=" + money +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                '}';
    }

    public HostModel() {
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
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

    public HostModel(int createBy, Date createTime, int updateBy, Date updateTime, String remark, int id, String name, String nick, String phone, String sfz, String email, String sex, int age, String address, String yhk, String status, String password, int hostId, String type, double money, int createBy1, Date createTime1) {
        super(createBy, createTime, updateBy, updateTime, remark, id, name, nick, phone, sfz, email, sex, age, address, yhk, status, password);
        this.hostId = hostId;
        this.type = type;
        this.money = money;
        this.createBy = createBy1;
        this.createTime = createTime1;
    }
}
