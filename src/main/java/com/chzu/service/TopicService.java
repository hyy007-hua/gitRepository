package com.chzu.service;

import com.chzu.model.TbTopic;

import java.util.List;

public interface TopicService {
    /**
     * 添加课题
     * @param topic
     * @param description
     * @param teaId
     */
    public void addTopic(String topic,String description,String teaId);

    /**
     * 根据教师id查询其所上传的选题信息
     * @param teaId
     * @return
     */
    public List<TbTopic> queryAllByTeaId(String teaId);

    /**
     * 根据学生id查询其教师所上传的选题信息
     * @param stuId
     * @return
     */
    public List<TbTopic> queryAllByStuId(String stuId);

    /**
     * 学生选择课题
     * @param stuId
     * @param topicId
     */
    public String chooseTopic(String stuId,String topicId);

    /**
     * 查询学生选题信息
     * @param stuId
     * @return
     */
    public TbTopic queryByStuId(String stuId);

    /**
     * 删除
     * @param id
     */
    public void deleteById(String id);
}
