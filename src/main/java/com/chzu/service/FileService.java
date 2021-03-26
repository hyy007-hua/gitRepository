package com.chzu.service;

import com.chzu.model.TbFile;

public interface FileService {
    /**
     * 删除
     * @param id
     */
    public void deleteById(String id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public TbFile getById(String id);
}
