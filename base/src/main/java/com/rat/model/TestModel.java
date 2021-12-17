package com.rat.model;

import java.util.Date;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/17 13:19
 */
public class TestModel extends BaseModel {
    private String id;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "TestModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public TestModel() {
    }

    public TestModel(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public TestModel(int createBy, Date createTime, int updateBy, Date updateTime, String remark, String id, String name, int age) {
        super(createBy, createTime, updateBy, updateTime, remark);
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
