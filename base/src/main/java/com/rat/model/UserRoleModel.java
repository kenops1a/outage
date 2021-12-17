package com.rat.model;

import java.util.Date;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/13 9:58
 */
public class UserRoleModel extends BaseModel {
    private int id;
    private String name;
    private String status;

    @Override
    public String toString() {
        return "UserRoleModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public UserRoleModel(int createBy, Date createTime, int updateBy, Date updateTime, String remark, int id, String name, String status) {
        super(createBy, createTime, updateBy, updateTime, remark);
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public UserRoleModel() {
    }

    public UserRoleModel(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
