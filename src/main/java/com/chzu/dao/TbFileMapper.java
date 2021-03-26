package com.chzu.dao;

import com.chzu.util.mymapper.MyMapper;
import com.chzu.model.TbFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbFileMapper extends MyMapper<TbFile> {

    public List<TbFile> getByUpIdAndFileType(@Param("upId") String upId, @Param("fileType") int fileType);
}