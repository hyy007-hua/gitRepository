package com.chzu.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_student")
public class TbStudent {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 学生编号
     */
    private String number;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 电话号码
     */
    @Column(name = "tel_number")
    private String telNumber;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 所属小组id
     */
    @Column(name = "group_id")
    private String groupId;

    /**
     * 所属班级id
     */
    @Column(name = "class_id")
    private String classId;

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
     * 获取学生编号
     *
     * @return number - 学生编号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置学生编号
     *
     * @param number 学生编号
     */
    public void setNumber(String number) {
        this.number = number;
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
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取电话号码
     *
     * @return tel_number - 电话号码
     */
    public String getTelNumber() {
        return telNumber;
    }

    /**
     * 设置电话号码
     *
     * @param telNumber 电话号码
     */
    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    /**
     * 获取邮箱地址
     *
     * @return email - 邮箱地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱地址
     *
     * @param email 邮箱地址
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取所属小组id
     *
     * @return group_id - 所属小组id
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置所属小组id
     *
     * @param groupId 所属小组id
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取所属班级id
     *
     * @return class_id - 所属班级id
     */
    public String getClassId() {
        return classId;
    }

    /**
     * 设置所属班级id
     *
     * @param classId 所属班级id
     */
    public void setClassId(String classId) {
        this.classId = classId;
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