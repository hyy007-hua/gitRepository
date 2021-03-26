package com.chzu.dao;

import com.chzu.util.mymapper.MyMapper;
import com.chzu.model.TbTeacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbTeacherMapper extends MyMapper<TbTeacher> {
    public TbTeacher getById(@Param("id")String id);

    public int resetPassword(@Param("id") String id,@Param("newPass") String newPass);

    public int updateInfo(@Param("number") String number,
                          @Param("sex") int sex,
                          @Param("email") String email,
                          @Param("telNumber") String telNumber);

}