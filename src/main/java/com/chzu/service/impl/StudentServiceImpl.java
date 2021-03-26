package com.chzu.service.impl;

import com.chzu.dao.TbClassMapper;
import com.chzu.dao.TbFileMapper;
import com.chzu.dao.TbStudentMapper;
import com.chzu.dao.TbTeacherMapper;
import com.chzu.enums.FileType;
import com.chzu.enums.Sex;
import com.chzu.enums.UserType;
import com.chzu.model.*;
import com.chzu.model.vo.StudentVO;
import com.chzu.service.StudentService;
import com.chzu.util.M5Util;
import com.chzu.util.ReadExcelUtil;
import com.chzu.util.SmallUtil;
import com.chzu.util.UploadFileUtil;
import com.chzu.util.layimInit.Friend;
import com.chzu.util.layimInit.LayIMInit;
import com.chzu.util.layimInit.Mine;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService{
    @Autowired
    private TbStudentMapper studentMapper;
    @Autowired
    private TbClassMapper classMapper;
    @Autowired
    private TbFileMapper fileMapper;
    @Autowired
    private TbTeacherMapper teacherMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean accountIsExisted(String account) {
        Example example = new Example(TbStudent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("number",account);

        TbStudent student = studentMapper.selectOneByExample(example);
        if(student == null){
            return false;
        }else {
            return true;
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public TbStudent queryByAccountAndPassword(String account, String password){
        Example example = new Example(TbStudent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("number",account);
        try {
            criteria.andEqualTo("password",M5Util.md5encode(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentMapper.selectOneByExample(example);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int save(TbStudent student) throws Exception{
        String psd = M5Util.md5encode(student.getNumber().substring(student.getNumber().length() - 6));
        String id = SmallUtil.getGeneratID();
        student.setId(id);
        student.setPassword(psd);
        student.setCreateTime(new Date());
        student.setUpdateTime(new Date());
        return studentMapper.insert(student);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PageInfo<StudentVO> getAll(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<TbStudent> studentList = studentMapper.selectAll();
        return getStudentVOParseFromTbStudentList(studentList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadExcelStudent(MultipartFile multFile,
                                      String upId,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        if(multFile.isEmpty()){
            throw new Exception("文件不存在！");
        }
        TbFile file = null;
        //将文件存至本地
        file = UploadFileUtil.uploadFile(multFile,request,upId,FileType.excel);

        if(file != null){
            //保存文件信息到数据库
            fileMapper.insert(file);

            String[][] result = ReadExcelUtil.parseInfoFromInputFile(file.getFilePath());
            String claId = null;

            System.out.println("班级信息："+file.getFileName());
            //检查数据库中班级信息是否存在
            Example example = new Example(TbClass.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("claName",file.getFileName());
            TbClass cla = classMapper.selectOneByExample(example);
            //该班级不存在
            if(cla == null){
                TbClass tbClass = new TbClass();
                claId = SmallUtil.getGeneratID();
                //文件名即班级名称
                tbClass.setClaName(file.getFileName());
                tbClass.setClaSize(0);
                tbClass.setId(claId);
                tbClass.setCreateTime(new Date());
                tbClass.setUpdateTime(new Date());
                classMapper.insert(tbClass);
            }else{
                claId = cla.getId();
            }

            //读取字符串二维数组，将学生信息存至数据库
            for(int i = 0; i < result.length; ++ i){
                if(result[i][0] == null)
                    break;
                else{
                    TbStudent student = new TbStudent();
                    student.setId(SmallUtil.getGeneratID());
                    student.setCreateTime(new Date());
                    student.setUpdateTime(new Date());
                    for(int j = 0;j < result[i].length; ++j){
                        if(result[i][j] == null)
                            break;
                        else{
                            switch (j){
                                case 0:
                                    student.setName(result[i][j]);
                                    break;
                                case 1:
                                    student.setNumber(result[i][j]);
                                    break;
                                case 2:
                                    //密码加密
                                    String pwd = M5Util.md5encode(result[i][j]);
                                    student.setPassword(pwd);
                                    break;
                                case 3:
                                    switch (result[i][j]){
                                        case "男":
                                            student.setSex(Sex.man.type);
                                            break;
                                        case "女":
                                            student.setSex(Sex.woman.type);
                                            break;
                                        default:
                                            student.setSex(404);
                                    }
                                case 4:
                                    student.setEmail(result[i][j]);
                                    break;
                                case 5:
                                    student.setTelNumber(result[i][j]);
                                    break;
                                case 6:
                                    student.setClassId(claId);
                                    break;

                            }
                        }
                    }
                    //将学生信息存至数据库
                    studentMapper.insert(student);
                }

                //更新班级人数
                TbStudent studentExam = new TbStudent();
                studentExam.setClassId(claId);
                int studentCount = studentMapper.selectCount(studentExam);
                classMapper.updateNumById(claId,studentCount);

            }
        }

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PageInfo<StudentVO> getListByTeaId(int page,int limit,String teaId){
        PageHelper.startPage(page,limit);
        List<TbStudent> studentList = studentMapper.getListByTeaId(teaId);
        return getStudentVOParseFromTbStudentList(studentList);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PageInfo<StudentVO> getByClaNameOrNumber(int page,int limit,String claName, String number) {
        PageHelper.startPage(page,limit);
        List<TbStudent> studentList = studentMapper.getByClaNameOrNumber(claName,number);
        return getStudentVOParseFromTbStudentList(studentList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInfo(String id, int sex, String email, String telNumber) {
        //从数据库查询学生信息
        TbStudent student = studentMapper.selectByPrimaryKey(id);
        //更新字段信息
        student.setEmail(email);
        student.setSex(sex);
        student.setNumber(telNumber);
        student.setUpdateTime(new Date());
        //更新数据库
        studentMapper.updateByPrimaryKey(student);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean confirmOldPassword(String id,String oldPwd){
        TbStudent student = studentMapper.selectByPrimaryKey(id);
        String pwd = student.getPassword();
        return M5Util.verify(oldPwd,pwd);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void resetPassword(String id,String newPassword){
        TbStudent student = studentMapper.selectByPrimaryKey(id);
        try {
            student.setPassword(M5Util.md5encode(newPassword));
            student.setUpdateTime(new Date());
        }catch (Exception e){
            e.printStackTrace();
        }
        studentMapper.updateByPrimaryKey(student);
    }

    private PageInfo<StudentVO> getStudentVOParseFromTbStudentList(List<TbStudent> studentList){
        List<StudentVO> studentVOList = new ArrayList<>();
        for(TbStudent stu : studentList){
            StudentVO stuVO = new StudentVO();
            stuVO.setId(stu.getId());
            stuVO.setNumber(stu.getNumber());
            stuVO.setName(stu.getName());
            stuVO.setEmail(stu.getEmail());
            switch (stu.getSex()){
                case 0:
                    stuVO.setSex(Sex.woman.value);
                    break;
                case 1:
                    stuVO.setSex(Sex.man.value);
                    break;
                case 2:
                    stuVO.setSex(Sex.secret.value);
            }
            stuVO.setCreateTime(stu.getCreateTime());
            stuVO.setClassName(classMapper.getClassNameById(stu.getClassId()));
            studentVOList.add(stuVO);
        }
        return new PageInfo<StudentVO>(studentVOList);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public LayIMInit init(String id) {
        LayIMInit layIMInit = new LayIMInit();
        TbStudent student = studentMapper.selectByPrimaryKey(id);

        Mine mine = new Mine();
        mine.setUsername(student.getName());
        mine.setStatus("online");
        mine.setAvatar("http://tp1.sinaimg.cn/5286730964/50/5745125631/0");
        mine.setId(id);
        layIMInit.setMine(mine);

        //查询好友
        Example example = new Example(TbStudent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("groupId",student.getGroupId());
        List<TbStudent> studentList = studentMapper.selectByExample(example);
        List<Mine> classmateList = new ArrayList<>();
        for(TbStudent stu : studentList){
            //排除自己
            if(stu.getId().equals(id)) continue;
            Mine mine1 = new Mine();
            mine1.setUsername(stu.getName());
            mine1.setId(stu.getId());
            mine1.setAvatar("http://tp1.sinaimg.cn/5286730964/50/5745125631/0");
            classmateList.add(mine1);
        }
        Friend friend1 = new Friend();
        friend1.setList(classmateList);
        friend1.setGroupname("同学列表");
        friend1.setId("0");

        //查询老师
        List<Mine> teacherGroupList = new ArrayList<>();
        List<TbTeacher> teacherList = teacherMapper.selectAll();
        for(TbTeacher teacher : teacherList){
            Mine mine1 = new Mine();
            mine1.setUsername(teacher.getName());
            mine1.setId(teacher.getId());
            mine1.setAvatar("http://tp1.sinaimg.cn/5286730964/50/5745125631/0");
            teacherGroupList.add(mine1);
        }
        Friend friend2 = new Friend();
        friend2.setList(teacherGroupList);
        friend2.setGroupname("教师列表");
        friend2.setId("1");

        List<Friend> friendList = new ArrayList<>();
        friendList.add(friend1);
        friendList.add(friend2);
        layIMInit.setFriend(friendList);

        return layIMInit;
    }

}
