package com.chzu.service;


import com.chzu.model.TbAdmin;
import com.chzu.util.layimInit.LayIMInit;

public interface AdminService {

    /**
     * 账号是否存在
     * @param account
     * @return
     */
    public boolean accountIsExisted(String account);

    /**
     * 根据账号、密码查询管理员信息
     * @param account
     * @param password
     * @return
     */
    public TbAdmin queryByAccountAndPassword(String account, String password);

    /**
     * 插入管理员张号、密码
     * @param account
     * @param password
     */
    public void save(String account,String password);

    /**
     * layui聊天窗口的初始化
     * @param id
     * @return
     */
    public LayIMInit init(String id);
}
