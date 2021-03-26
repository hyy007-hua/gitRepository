package com.chzu.dao;

import com.chzu.util.mymapper.MyMapper;
import com.chzu.model.TeaClass;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeaClassMapper extends MyMapper<TeaClass> {
    public void delLinkTeacherAndClass(@Param("teaId") String teaId, @Param("claId") String claId);
}