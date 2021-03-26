package com.chzu.model;

import javax.persistence.*;

@Table(name = "tb_topic")
public class TbTopic {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 课题
     */
    private String topic;

    /**
     * 描述内容
     */
    private String description;

    /**
     * 课题所属老师
     */
    @Column(name = "tea_id")
    private String teaId;

    /**
     * 选择该课题的小组id
     */
    @Column(name = "gro_id")
    private String groId;

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
     * 获取课题
     *
     * @return topic - 课题
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 设置课题
     *
     * @param topic 课题
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * 获取描述内容
     *
     * @return description - 描述内容
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述内容
     *
     * @param description 描述内容
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取课题所属老师
     *
     * @return tea_id - 课题所属老师
     */
    public String getTeaId() {
        return teaId;
    }

    /**
     * 设置课题所属老师
     *
     * @param teaId 课题所属老师
     */
    public void setTeaId(String teaId) {
        this.teaId = teaId;
    }

    /**
     * 获取选择该课题的小组id
     *
     * @return gro_id - 选择该课题的小组id
     */
    public String getGroId() {
        return groId;
    }

    /**
     * 设置选择该课题的小组id
     *
     * @param groId 选择该课题的小组id
     */
    public void setGroId(String groId) {
        this.groId = groId;
    }
}