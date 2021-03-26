package com.chzu.service.impl;


import com.chzu.dao.TbClassMapper;
import com.chzu.model.TbClass;
import com.chzu.model.vo.TransferData;
import com.chzu.service.ClassService;
import com.chzu.util.SmallUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service("classService")
public class ClassServiceImpl implements ClassService{

    @Autowired
    private TbClassMapper classMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(String claName) {
        TbClass cl = new TbClass();
        cl.setId(SmallUtil.getGeneratID());
        cl.setClaName(claName);
        cl.setClaSize(0);

        classMapper.insert(cl);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<TbClass> getAll() {
        return classMapper.selectAll();
    }

    /*
    @Override
    public List<ClassEntity> getList(int page, int limit) {
        int start = (page-1)*limit;
        return classDao.getList(start,limit);
    }

    @Override
    public List<ClassEntity> getByClaName(String claName) {
        return classDao.getByClaName(claName);
    }

    @Override
    public List<ClassEntity> getDelList(int page, int limit) {
        int start = (page-1)*limit;
        return classDao.getDelList(start,limit);
    }*/

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<TransferData> getClassInfoByTeaId(String teaId) {
        List<TransferData> dataList = new ArrayList<>();
        List<TbClass> noShipClassList = classMapper.getNotBuildedRelationship(teaId);
        for(TbClass cla: noShipClassList){
            TransferData data = new TransferData();
            data.setValue(cla.getId());
            data.setTitle(cla.getClaName());
            data.setIsRight("false");
            dataList.add(data);
        }
        List<TbClass> classList = classMapper.getBuildedRelationship(teaId);
        for(TbClass cla: classList){
            TransferData data = new TransferData();
            data.setValue(cla.getId());
            data.setTitle(cla.getClaName());
            data.setIsRight("true");
            dataList.add(data);
        }
        return dataList;
    }
    /*

    @Override
    public int changeState(long id, int del) {
        if(del == 1){
            return classDao.resumeById(id);
        }else{
            return classDao.delById(id);
        }
    }

    @Override
    public List<ClassEntity> getClassesByTeacherId(long teacherId) {
        return classDao.getClassesByTeacherId(teacherId);
    }*/
}
