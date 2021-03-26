package com.chzu.service;


import com.chzu.model.TbTeacher;
import com.chzu.model.vo.TeacherVO;
import com.chzu.model.vo.TreeData;
import com.chzu.util.layimInit.LayIMInit;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface TeacherService {

    /**
     *
     * @param teacher
     * @return
     */
    public int save(TbTeacher teacher);

    /**
     * 教师与班级建立关系
     * @param teaId
     * @param classIds
     * @return
     */
    public int linkTeacherAndClass(String teaId, String[] classIds);

    /**
     * 教师与班级删除关系
     * @param teaId
     * @param classIds
     * @return
     */
    public int delLinkTeacherAndClass(String teaId,String[] classIds);

    /**
     * 判断账号是否存在
     * @param account
     * @return
     */
    public boolean accountIsExisted(String account);

    /**
     * 根据教师账号和密码查询教师信息
     * //@param account
     * //@param password
     * @return
     */
    public TbTeacher getByAccountAndPassword(String account, String password);

    /**
     * 查询所有教师信息
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PageInfo<TeacherVO> getAll(int pageIndex,int pageSize);

    /**
     * 批量上传教师信息，存至数据库
     * @param multFile
     * @param upId
     * @param request
     */
    public void uploadTeacherExcel(MultipartFile multFile , String upId, HttpServletRequest request) throws Exception;

    /**
     * 确认旧密码输入是否正确
     * @param id
     * @param oldPassword
     * @return
     */
    public boolean confirmOldPassword(String id,String oldPassword);

    /**
     * 重置密码
     * @param id
     * @param newPass
     * @return
     */
    public boolean resetPassword(String id,String newPass);

    /**
     * 上传大纲或者课程审查表
     * @param multFile
     * @param upId
     * @param fileType
     * @param request
     */
    public void uploadData(MultipartFile multFile , String upId, String fileType, HttpServletRequest request) throws IOException;

    /**
     * 据教师id查询大纲及课程审查表相关信息
     * @param teaId
     * @return
     */
    public List<TreeData> queryDataByTeaId(String teaId);

    /**
     * layui聊天窗口的初始化
     * @param id
     * @return
     */
    public LayIMInit init(String id);

    /**
     * 更新教师信息
     * @param number
     * @param sex
     * @param email
     * @param telNumber
     * @return
     */
    public int updateInfo(String number,int sex,String email,String telNumber);


}
