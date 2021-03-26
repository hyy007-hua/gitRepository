package com.chzu.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_group")
public class TbGroup {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 小组名称
     */
    @Column(name = "gro_name")
    private String groName;

    /**
     * 小组成绩
     */
    @Column(name = "gro_score")
    private Integer groScore;

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
     * 获取小组名称
     *
     * @return gro_name - 小组名称
     */
    public String getGroName() {
        return groName;
    }

    /**
     * 设置小组名称
     *
     * @param groName 小组名称
     */
    public void setGroName(String groName) {
        this.groName = groName;
    }

    /**
     * 获取小组成绩
     *
     * @return gro_score - 小组成绩
     */
    public Integer getGroScore() {
        return groScore;
    }

    /**
     * 设置小组成绩
     *
     * @param groScore 小组成绩
     */
    public void setGroScore(Integer groScore) {
        this.groScore = groScore;
    }
}