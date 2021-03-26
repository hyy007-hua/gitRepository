package com.chzu.dao;

import com.chzu.util.mymapper.MyMapper;
import com.chzu.model.TbStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbStudentMapper extends MyMapper<TbStudent> {
    public List<TbStudent> getListByTeaId(@Param("teaId") String teaId);

    public List<TbStudent> getByClaNameOrNumber(@Param("claName") String claName,@Param("number") String number);
}