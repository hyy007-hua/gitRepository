package com.chzu.service.impl;

import com.chzu.dao.TbStudentMapper;
import com.chzu.dao.TbTopicMapper;
import com.chzu.dao.TeaClassMapper;
import com.chzu.model.TbStudent;
import com.chzu.model.TbTopic;
import com.chzu.model.TeaClass;
import com.chzu.service.TopicService;
import com.chzu.util.SmallUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("topicService")
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TbTopicMapper topicMapper;
    @Autowired
    private TbStudentMapper studentMapper;
    @Autowired
    private TeaClassMapper teaClassMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addTopic(String topic, String description, String teaId) {
        TbTopic topicModel = new TbTopic();
        topicModel.setDescription(description);
        topicModel.setId(SmallUtil.getGeneratID());
        topicModel.setTeaId(teaId);
        topicModel.setTopic(topic);
        topicMapper.insert(topicModel);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<TbTopic> queryAllByTeaId(String teaId) {
        Example example = new Example(TbTopic.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teaId",teaId);
        return topicMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<TbTopic> queryAllByStuId(String stuId) {
        TbStudent student = studentMapper.selectByPrimaryKey(stuId);

        Example example = new Example(TeaClass.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("claId",student.getClassId());
        TeaClass teaClass = teaClassMapper.selectOneByExample(example);

        return this.queryAllByTeaId(teaClass.getTeaId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String chooseTopic(String stuId, String topicId) {
        TbStudent student = studentMapper.selectByPrimaryKey(stuId);
        //确认该同学是否建立团队，拥有选题的权利
        if(student.getGroupId() == null || student.getGroupId().equals("")){
            return "您还未组建团队，不具备选择课题的权利！";
        }
        //如果有选题权利，确认该团队是否已经选过课题
        Example example = new Example(TbTopic.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("groId",student.getGroupId());
        TbTopic chooseTopic = topicMapper.selectOneByExample(example);
        if(chooseTopic != null){
            return "您已经选择过课题！";
        }
        //确认课题是否被选
        TbTopic topic = topicMapper.selectByPrimaryKey(topicId);
        if(topic.getGroId() != null && !topic.getGroId().equals("")){
            return "该课题已被选，请选择其他课题！";
        }
        topic.setGroId(student.getGroupId());
        int result = topicMapper.updateByPrimaryKey(topic);
        if(result == 1){
            return "success";
        }else {
            return "出错！";
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public TbTopic queryByStuId(String stuId) {
        TbStudent student = studentMapper.selectByPrimaryKey(stuId);
        Example example = new Example(TbTopic.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("groId",student.getGroupId());
        return topicMapper.selectOneByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(String id) {
        topicMapper.deleteByPrimaryKey(id);
    }
}
