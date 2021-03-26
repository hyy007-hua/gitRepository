package com.chzu.service;

import com.chzu.model.TbStudent;
import com.chzu.model.vo.StudentVO;
import com.chzu.util.layimInit.LayIMInit;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;

public interface StudentService {
    /**
     * 验证账号是否存在
     * @param account
     * @return
     */
    public boolean accountIsExisted(String account);

    /**
     * 根据账号和密码查询
     * @param account
     * @param password
     * @return
     */
    public TbStudent queryByAccountAndPassword(String account, String password);

    /**
     * 保存学生信息
     * @param student
     * @return
     * @throws Exception
     */
    public int save(TbStudent student) throws Exception;

    /**
     * 查询所有学生信息
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PageInfo<StudentVO> getAll(Integer pageIndex, Integer pageSize);

    /**
     * 批量上传学生信息
     * @param file
     * @param request
     * @param response
     * @return
     */
    public void uploadExcelStudent(MultipartFile file, String upId, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 根据教师id查询学生信息，即教师带的班级的学生信息
     * @param page
     * @param limit
     * @param teaId
     * @return
     */
    public PageInfo<StudentVO> getListByTeaId(int page,int limit,String teaId);

    /**
     * 根据班级名或者学生学号查询
     * @param claName
     * @param number
     * @return
     */
    public PageInfo<StudentVO> getByClaNameOrNumber(int page,int limit,String claName, String number);

    /**
     * 更新学生信息
     * @param sex
     * @param email
     * @param telNumber
     */
    public void updateInfo(String id,int sex,String email,String telNumber);

    /**
     * 确认旧密码是否正确
     * @param id
     * @param oldPwd
     * @return
     */
    public boolean confirmOldPassword(String id,String oldPwd);

    /**
     * 更新密码
     * @param id
     * @param newPassword
     */
    public void resetPassword(String id,String newPassword);

    /**
     * layui聊天窗口的初始化
     * @param id
     * @return
     */
    public LayIMInit init(String id);
}
