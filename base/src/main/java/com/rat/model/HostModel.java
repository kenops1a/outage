package com.rat.model;

import java.util.Date;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/13 13:54
 */
public class HostModel extends UserModel {
    private int hostId;
    private String type;
    private double money;

    public HostModel() {
    }

    public HostModel(int createBy, Date createTime, int updateBy, Date updateTime, String remark, int id, String name, String nick, String phone, String sfz, String email, String sex, int age, String address, String yhk, String status, String password, int hostId, String type, double money) {
        super(createBy, createTime, updateBy, updateTime, remark, id, name, nick, phone, sfz, email, sex, age, address, yhk, status, password);
        this.hostId = hostId;
        this.type = type;
        this.money = money;
    }

    @Override
    public String toString() {
        return "HostModel{" +
                "hostId=" + hostId +
                ", type='" + type + '\'' +
                ", money=" + money +
                '}';
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
}
