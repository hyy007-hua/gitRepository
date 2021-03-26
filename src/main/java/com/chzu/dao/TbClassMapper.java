package com.chzu.dao;

import com.chzu.util.mymapper.MyMapper;
import com.chzu.model.TbClass;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbClassMapper extends MyMapper<TbClass> {

    public String getClassNameById(String id);

    public void updateNumById(@Param("id") String id, @Param("count") int count);

    /**
     * 查询与教师未建立联系的班级
     * @param teaId
     * @return
     */
    public List<TbClass> getNotBuildedRelationship(@Param("teaId") String teaId);

    /**
     * 查询与教师建立联系的班级
     * @param teaId
     * @return
     */
    public List<TbClass> getBuildedRelationship(@Param("teaId") String teaId);
}