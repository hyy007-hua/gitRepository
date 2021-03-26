package com.chzu.service.impl;


import com.chzu.dao.TbFileMapper;
import com.chzu.dao.TbStudentMapper;
import com.chzu.dao.TbTeacherMapper;
import com.chzu.dao.TeaClassMapper;
import com.chzu.enums.FileType;
import com.chzu.enums.Sex;
import com.chzu.model.TbFile;
import com.chzu.model.TbStudent;
import com.chzu.model.TbTeacher;
import com.chzu.model.TeaClass;
import com.chzu.model.vo.TeacherVO;
import com.chzu.model.vo.TreeChildren;
import com.chzu.model.vo.TreeData;
import com.chzu.service.TeacherService;
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
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("teacherService")
public class TeacherServiceImpl  implements TeacherService{
    @Autowired
    private TbTeacherMapper teacherMapper;
    @Autowired
    private TbFileMapper fileMapper;
    @Autowired
    private TeaClassMapper teaClassMapper;
    @Autowired
    private TbStudentMapper studentMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int save(TbTeacher teacher){
        String number = teacher.getNumber();
        teacher.setId(SmallUtil.getGeneratID());
        try{
            //初始密码设为教师编号后六位，并加密
            teacher.setPassword(M5Util.md5encode(number.substring(number.length() - 6)));
        }catch (Exception e){
            e.printStackTrace();
        }
        teacher.setUpdateTime(new Date());
        teacher.setCreateTime(new Date());
        return teacherMapper.insert(teacher);
    }

    /*
    @Override
    public List<TeacherEntity> getByTimeOrNumber(String start, String end, String number,int del) throws ParseException {
        String time = " 00:00:00";
        if(start != null && start != "" && end != null && end != ""){
            Timestamp t1 = SmallUtil.getTimestamp(start+time);
            Timestamp t2 = SmallUtil.getTimestamp(end+time);
            return  teacherDao.getByTimeOrNumber(t1,t2,number,del);
        }else {
            return teacherDao.getByTimeOrNumber(null,null,number,del);
        }
    }*/

    /*
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int delMoreByIds(String ids) {
        String[] id = new String[15];
        id = ids.split(",");
        int result = 0;
        for(int i=0;i<id.length;i++){
            if(id[i] != null ){
                teacherDao.delById(Long.parseLong(id[i]));
                result ++;
            }
        }
        return result;
    }

    @Override
    public int resumeById(long id) {
        return teacherDao.resumeById(id);
    }
    */

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int linkTeacherAndClass(String teaId,String[] classIds) {
        int result = 0;
        for(int i=0;i<classIds.length;i++){
            if(classIds[i] != null && classIds[i] != ""){
                TeaClass tc = new TeaClass();
                tc.setId(SmallUtil.getGeneratID());
                tc.setClaId(classIds[i]);
                tc.setTeaId(teaId);
                if(teaClassMapper.insert(tc) == 1){
                    result ++;
                }
            }

        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int delLinkTeacherAndClass(String teaId, String[] classIds){
        int result = 0;
        for(int i=0;i<classIds.length;i++){
            if(classIds[i] != null && classIds[i] != ""){
                teaClassMapper.delLinkTeacherAndClass(teaId,classIds[i]);
                result ++;
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean accountIsExisted(String account) {
        Example example = new Example(TbTeacher.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("number",account);
        TbTeacher teacher = teacherMapper.selectOneByExample(example);
        if(teacher == null){
            return false;
        }else{
            return true;
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public TbTeacher getByAccountAndPassword(String account,String password) {
        Example example = new Example(TbTeacher.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("number",account);
        try {
            criteria.andEqualTo("password",M5Util.md5encode(password));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teacherMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PageInfo<TeacherVO> getAll(int pageIndex, int pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        List<TbTeacher> teacherList = teacherMapper.selectAll();
        List<TeacherVO> teacherVOS = new ArrayList<>();
        for(TbTeacher teacher : teacherList){
            TeacherVO teacherVO = new TeacherVO();
            teacherVO.setId(teacher.getId());
            teacherVO.setName(teacher.getName());
            teacherVO.setPassword(teacher.getPassword());
            teacherVO.setEmail(teacher.getEmail());
            teacherVO.setNumber(teacher.getNumber());
            switch (teacher.getSex()){
                case 0:
                    teacherVO.setSex(Sex.woman.value);
                    break;
                case 1:
                    teacherVO.setSex(Sex.man.value);
                    break;
                default:
                    teacherVO.setSex(Sex.secret.value);
            }
            teacherVO.setTelNumber(teacher.getTelNumber());
            teacherVO.setCreateTime(teacher.getCreateTime());
            teacherVO.setUpdateTime(teacher.getUpdateTime());
            teacherVOS.add(teacherVO);
        }
        return new PageInfo<>(teacherVOS);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void uploadTeacherExcel(MultipartFile multFile,
                                   String upId,
                                   HttpServletRequest request) throws Exception{
        if(multFile.isEmpty()){
            throw new Exception("文件不存在！");
        }
        TbFile file = null;
        //将文件存至本地
        file = UploadFileUtil.uploadFile(multFile,request,upId,FileType.excel);
        if(file != null){
            //文件相关信息存至数据库
            fileMapper.insert(file);

            //读取文件内信息
            String[][] result = ReadExcelUtil.parseInfoFromInputFile(file.getFilePath());
            for(int i = 0;i <result.length; ++ i){
                if(result[i][0] != null){
                    TbTeacher teacher = new TbTeacher();
                    for(int j=0;j < result[i].length; ++j){
                        if(result[i][j] != null){
                            String info = result[i][j];
                            switch (j){
                                case 0:
                                    teacher.setName(info);
                                    break;
                                case 1:
                                    teacher.setNumber(info);
                                    break;
                                case 2:
                                    //密码加密
                                    teacher.setPassword(M5Util.md5encode(info));
                                    break;
                                case 3:
                                    switch (info){
                                        case "男":
                                            teacher.setSex(Sex.man.type);
                                            break;
                                        case "女":
                                            teacher.setSex(Sex.woman.type);
                                            break;
                                        default:
                                            teacher.setSex(404);
                                    }
                                    break;
                                case 4:
                                    teacher.setEmail(info);
                                    break;
                                case 5:
                                    teacher.setTelNumber(info);
                                    break;
                            }
                        }
                    }
                    //避免数据重复
                    if(teacherMapper.selectOne(teacher) == null){
                        //单条数据存至数据库
                        teacher.setCreateTime(new Date());
                        teacher.setUpdateTime(new Date());
                        teacher.setId(SmallUtil.getGeneratID());
                        teacherMapper.insert(teacher);
                    }

                }
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean confirmOldPassword(String id, String oldPassword) {
        TbTeacher teacher = teacherMapper.getById(id);
        if(M5Util.verify(oldPassword,teacher.getPassword()))
            return true;
        else
            return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean resetPassword(String id ,String newPass) {
        int result = 0;
        try{
            result = teacherMapper.resetPassword(id,M5Util.md5encode(newPass));
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result == 1)
            return true;
        else
            return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadData(MultipartFile multFile, String upId, String fileType, HttpServletRequest request) throws IOException {
        TbFile file = null;
        //将文件存至本地
        file = UploadFileUtil.uploadFile(multFile,request,upId, FileType.valueOf(fileType));
        if(file != null) {
            //文件相关信息存至数据库
            fileMapper.insert(file);
        }

        //根据上传的excel分组名单表分组
        if(fileType == FileType.groupList.value){

        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<TreeData> queryDataByTeaId(String teaId) {
        List<TreeData> treeDataList = new ArrayList<>();
        //建立树的一级分类  大纲
        TreeData outline = new TreeData();
        outline.setId("1");
        outline.setName("大纲");
        List<TbFile> outlineFileList = fileMapper.getByUpIdAndFileType(teaId,FileType.outline.type);
        //建立大纲的二级分类
        List<TreeChildren> outlineChildren = new ArrayList<>();
        for(TbFile outlineFile : outlineFileList){
            TreeChildren c = new TreeChildren();
            c.setId(outlineFile.getId());
            c.setName(outlineFile.getFileName());
            outlineChildren.add(c);
        }
        outline.setChildren(outlineChildren);

        treeDataList.add(outline);

        //建立树的一级分类  课程审查表
        TreeData courseCheckForm = new TreeData();
        courseCheckForm.setId("2");
        courseCheckForm.setName("课程审查表");
        List<TbFile> courseFileList = fileMapper.getByUpIdAndFileType(teaId,FileType.courseExamineForm.type);
        //建立课程审查表的二级分类
        List<TreeChildren> courseChildren = new ArrayList<>();
        for(TbFile courseCheckFile : courseFileList){
            TreeChildren c = new TreeChildren();
            c.setId(courseCheckFile.getId());
            c.setName(courseCheckFile.getFileName());
            courseChildren.add(c);
        }
        courseCheckForm.setChildren(courseChildren);
        treeDataList.add(courseCheckForm);
        return treeDataList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateInfo(String number,int sex,String email,String telNumber){
        return teacherMapper.updateInfo( number, sex, email, telNumber);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public LayIMInit init(String id) {
        LayIMInit layIMInit = new LayIMInit();
        //添加自己
        TbTeacher ower = teacherMapper.selectByPrimaryKey(id);
        Mine mine = new Mine();
        mine.setId(ower.getId());
        mine.setUsername(ower.getName());
        mine.setAvatar("http://tp1.sinaimg.cn/5286730964/50/5745125631/0");
        mine.setStatus("online");
        layIMInit.setMine(mine);

        //查询学生
        List<TbStudent> studentList = studentMapper.getListByTeaId(id);
        List<Mine> classmateList = new ArrayList<>();
        for(TbStudent stu : studentList){
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
            //排除自己
            if(teacher.getId().equals(id)) continue;
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