package com.chzu.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_class")
public class TbClass {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 班级名称
     */
    @Column(name = "cla_name")
    private String claName;

    /**
     * 班级人数
     */
    @Column(name = "cla_size")
    private Integer claSize;

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
     * 获取班级名称
     *
     * @return cla_name - 班级名称
     */
    public String getClaName() {
        return claName;
    }

    /**
     * 设置班级名称
     *
     * @param claName 班级名称
     */
    public void setClaName(String claName) {
        this.claName = claName;
    }

    /**
     * 获取班级人数
     *
     * @return cla_size - 班级人数
     */
    public Integer getClaSize() {
        return claSize;
    }

    /**
     * 设置班级人数
     *
     * @param claSize 班级人数
     */
    public void setClaSize(Integer claSize) {
        this.claSize = claSize;
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