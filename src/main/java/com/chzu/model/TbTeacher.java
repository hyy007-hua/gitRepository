package com.chzu.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_teacher")
public class TbTeacher {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 教师编号
     */
    private String number;

    /**
     * 教师姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 电子邮箱地址
     */
    private String email;

    /**
     * 联系电话
     */
    @Column(name = "tel_number")
    private String telNumber;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取教师编号
     *
     * @return number - 教师编号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置教师编号
     *
     * @param number 教师编号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取教师姓名
     *
     * @return name - 教师姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置教师姓名
     *
     * @param name 教师姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取电子邮箱地址
     *
     * @return email - 电子邮箱地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮箱地址
     *
     * @param email 电子邮箱地址
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取联系电话
     *
     * @return tel_number - 联系电话
     */
    public String getTelNumber() {
        return telNumber;
    }

    /**
     * 设置联系电话
     *
     * @param telNumber 联系电话
     */
    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}