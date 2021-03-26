package com.chzu.service.impl;


import com.chzu.dao.TbAdminMapper;
import com.chzu.model.TbAdmin;
import com.chzu.service.AdminService;
import com.chzu.service.TeacherService;
import com.chzu.util.Constant;
import com.chzu.util.M5Util;
import com.chzu.util.SmallUtil;
import com.chzu.util.layimInit.Friend;
import com.chzu.util.layimInit.LayIMInit;
import com.chzu.util.layimInit.Mine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TbAdminMapper adminMapper;
    /*
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private GroupDao groupDao;*/

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public TbAdmin queryByAccountAndPassword(String account,String password){

        Example example = new Example(TbAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("account",account);
        try {
            criteria.andEqualTo("password",M5Util.md5encode(password));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return adminMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean accountIsExisted(String account) {

        Example example = new Example(TbAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("account",account);

        TbAdmin admin = adminMapper.selectOneByExample(example);
        if( admin == null){
            return false;
        }else{
            return true;
        }
    }

    @Transactional(propagation =  Propagation.REQUIRED)
    @Override
    public void save(String account, String password) {
        TbAdmin admin = new TbAdmin();
        admin.setId(SmallUtil.getGeneratID());
        admin.setAccount(account);
        try {
            admin.setPassword(M5Util.md5encode(password));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //管理员信息插入数据库
        adminMapper.insert(admin);
    }

    @Transactional
    @Override
    public LayIMInit init(String id) {/*
        LayIMInit layIMInit = new LayIMInit();
        //查询进入ChatRoom用户信息，此处为管理员的信息
        Mine mine = adminDAO.getMineById(id);
        mine.setStatus("online");
        mine.setUsername("admin");
        layIMInit.setMine(mine);
        Friend friend = new Friend();
        friend.setGroupname("好友列表");
        friend.setId(0);
        //查询用户的好友信息表，此处为管理员的好友（所有老师）的信息表
        List<ChatUser> users = teacherDao.getAllTeacher();
        friend.setList(users);
        int online = 0;
        for(ChatUser user : users){
            if(Constant.onlineUsers.containsKey(String.valueOf(user.getId()))){
                user.setStatus("online");
                online++;
            }else{
                user.setStatus("offline");
            }
        }
        friend.setOnline(online);
        List<Friend> friends = new ArrayList<>();
        friends.add(friend);
        layIMInit.setFriend(friends);
        //查询用户加入的群的信息
        List<ChatGroup> groups = groupDao.getByUserId(id);
        layIMInit.setGroup(groups);*/
        return null;
    }
}
