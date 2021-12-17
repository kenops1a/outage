package com.rat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/13 10:02
 */
public class RoleMenuModel implements Serializable {
    private int id;
    private String name;
    private String path;
    private int pid;

    @Override
    public String toString() {
        return "RoleMenuModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", pid=" + pid +
                '}';
    }
    public RoleMenuModel() {
    }

    public RoleMenuModel(int id, String name, String path, int pid) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.pid = pid;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
