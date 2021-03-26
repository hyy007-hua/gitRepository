package com.chzu.service.impl;

import com.chzu.dao.TbFileMapper;
import com.chzu.model.TbFile;
import com.chzu.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service("fileService")
public class FileServiceImpl implements FileService {
    @Autowired
    private TbFileMapper fileMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(String id) {
        TbFile file = new TbFile();
        file.setId(id);
        fileMapper.delete(file);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public TbFile getById(String id) {
        Example example = new Example(TbFile.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",id);
        return fileMapper.selectOneByExample(example);
    }
}
