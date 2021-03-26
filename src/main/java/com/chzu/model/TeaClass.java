package com.chzu.model;

import javax.persistence.*;

@Table(name = "tea_class")
public class TeaClass {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 教师id
     */
    @Column(name = "tea_id")
    private String teaId;

    /**
     * 班级id
     */
    @Column(name = "cla_id")
    private String claId;

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
     * 获取教师id
     *
     * @return tea_id - 教师id
     */
    public String getTeaId() {
        return teaId;
    }

    /**
     * 设置教师id
     *
     * @param teaId 教师id
     */
    public void setTeaId(String teaId) {
        this.teaId = teaId;
    }

    /**
     * 获取班级id
     *
     * @return cla_id - 班级id
     */
    public String getClaId() {
        return claId;
    }

    /**
     * 设置班级id
     *
     * @param claId 班级id
     */
    public void setClaId(String claId) {
        this.claId = claId;
    }
}