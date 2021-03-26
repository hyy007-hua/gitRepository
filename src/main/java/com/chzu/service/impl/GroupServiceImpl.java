package com.chzu.service.impl;

import com.chzu.dao.TbGroupMapper;
import com.chzu.dao.TbStudentMapper;
import com.chzu.model.TbGroup;
import com.chzu.model.TbStudent;
import com.chzu.service.GroupService;
import com.chzu.util.SmallUtil;
import com.chzu.util.layimInit.GroupMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service("groupService")
public class GroupServiceImpl implements GroupService {
    @Autowired
    private TbGroupMapper groupMapper;
    @Autowired
    private TbStudentMapper studentMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void randomGrouping(String claId, int memberNumber) {
        //随机分组的实现

        //查询某个班级的学生
        Example example = new Example(TbStudent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("classId",claId);
        List<TbStudent> studentList = studentMapper.selectByExample(example);
        int length = studentList.size();
        //得到不重复的随机数组，根据随机数组取特定人数（memberNumber）的学生，建立分组
        int[] randomList = SmallUtil.createRandomList(length-1,0);
        int index = 0;
        //得到分组的小组个数
        int groupNumber = (length / memberNumber) + 1;
        for(int i = 0;i < groupNumber; i ++){
            String groId = SmallUtil.getGeneratID();
            TbGroup group = new TbGroup();
            group.setId(groId);
            group.setGroName(claId+"-第"+(i+1)+"组");
            group.setGroScore(0);
            for(int j = 0;j < memberNumber; j++){
                if(index <= length-1 ){
                    TbStudent student = studentList.get(randomList[index]);
                    student.setGroupId(groId);
                    student.setUpdateTime(new Date());
                    studentMapper.updateByPrimaryKey(student);
                    index++;
                }
            }
            groupMapper.insert(group);
        }
    }

    @Override
    public GroupMember getMemberByGroId(long groId) {
        GroupMember members = new GroupMember();

        return null;
    }

}
