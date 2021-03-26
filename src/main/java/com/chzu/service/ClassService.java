package com.chzu.service;


import com.chzu.model.TbClass;
import com.chzu.model.vo.TransferData;

import java.util.List;

public interface ClassService {
    public void save(String claName);

    /**
     * 查询所有班级
     * @return
     */
    public List<TbClass> getAll();

    /**
     * 根据教师id查询是否建立联系的班级信息
     * @param teaId
     * @return
     */
    public List<TransferData> getClassInfoByTeaId(String teaId);
    /*
    public List<ClassEntity> getList(int page,int limit);
    public List<ClassEntity> getByClaName(String claName);
    public List<ClassEntity> getDelList(int page,int limit);
    //根据教师id查询未与教师建立关系的班级

    public int changeState(long id,int del);
    public List<ClassEntity> getClassesByTeacherId(long teacherId);*/
}
