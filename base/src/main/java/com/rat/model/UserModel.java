package com.rat.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/10 9:44
 */
public class UserModel extends BaseModel{

    private int id;
    private String name;
    @NotNull
    private String nick;
    private String phone;
    private String sfz;
    @NotNull
    private String email;
    private String sex;
    private int age;
    private String address;
    private String yhk;
    private String status;
    @NotNull
    private String password;

    public UserModel() {
    }

    public UserModel(int createBy, Date createTime, int updateBy, Date updateTime, String remark, int id, String name, String nick, String phone, String sfz, String email, String sex, int age, String address, String yhk, String status, String password) {
        super(createBy, createTime, updateBy, updateTime, remark);
        this.id = id;
        this.name = name;
        this.nick = nick;
        this.phone = phone;
        this.sfz = sfz;
        this.email = email;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.yhk = yhk;
        this.status = status;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", phone='" + phone + '\'' +
                ", sfz='" + sfz + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", yhk='" + yhk + '\'' +
                ", status='" + status + '\'' +
                ", password='" + password + '\'' +
                '}';
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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getYhk() {
        return yhk;
    }

    public void setYhk(String yhk) {
        this.yhk = yhk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
