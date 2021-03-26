package com.chzu.controller;

import com.chzu.model.TbStudent;
import com.chzu.model.vo.StudentVO;
import com.chzu.service.StudentService;
import com.chzu.util.JSONResult;
import com.chzu.util.TableDate;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value = "学生",tags = "与学生相关的接口")
@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @ApiOperation(value = "保存学生信息",notes = "保存学生信息",httpMethod = "POST")
    @PostMapping("save")
    @ResponseBody
    public JSONResult save(TbStudent student)throws Exception{
        if(studentService.save(student) == 1){
            return JSONResult.ok();
        }else{
            return JSONResult.errorMsg("添加失败");
        }
    }

    @ApiOperation(value = "查询所有学生信息",notes = "查询所有学生信息",httpMethod = "GET")
    @GetMapping("getAll")
    @ResponseBody
    public TableDate getAll(int page,int limit){
        PageInfo<StudentVO> students =  studentService.getAll(page,limit);

        return TableDate.ok(students.getList(),students.getSize());

    }

    @ApiOperation(value = "getListByTeaId",notes = "根据教师id查询其教授的学生信息",httpMethod = "GET")
    @GetMapping("getListByTeaId")
    @ResponseBody
    public TableDate getListByTeaId(
            int page,
            int limit,
            @ApiParam(name = "teaId",value = "教师id",required = true,example = "1212121212")
                    String teaId){
        PageInfo<StudentVO> students = studentService.getListByTeaId(page,limit,teaId);
        return TableDate.ok(students.getList(),students.getSize());
    }

    /*@ApiOperation(value = "查询所有已删除学生信息",notes = "查询所有已删除学生信息",httpMethod = "GET")
    @GetMapping("getDelAll")
    @ResponseBody
    public TableDate getDelAll(int page,int limit){
        List<Student> students = studentService.getDelAll(page,limit);
        if(students.size() > 0){
            return TableDate.ok(students,students.size());
        }else{
            return TableDate.error("无相关数据");
        }
    }*/

    @ApiOperation(value = "根据班级名或者学生学号查询所有学生信息",notes = "根据班级名或者学生学号查询所有学生信息",httpMethod = "GET")
    @GetMapping("getByClaNameOrNumber")
    @ResponseBody
    public TableDate getByClaNameOrNumber(
            int page,
            int limit,
            @ApiParam(name = "claName",value = "班级名",required = true,example = "1212121212")
            String claName,
            @ApiParam(name = "number",value = "学生学号",required = false,example = "2017211684")
            String number) {
        PageInfo<StudentVO> students = studentService.getByClaNameOrNumber(page,limit,claName,number);
        return TableDate.ok(students.getList(),students.getSize());
    }

    @ApiOperation(value = "更新学生信息",notes = "更新学生信息",httpMethod = "POST")
    @PostMapping("updateInfo")
    @ResponseBody
    public JSONResult updateInfo(
            @ApiParam(name = "id",value = "id",required = true,example = "1234552225412551")
            String id,
            @ApiParam(name = "number",value = "教师编号",required = false,example = "2020001")
            String number,
            @ApiParam(name = "sex",value = "教师性别",required = true,example = "1")
            int sex,
            @ApiParam(name = "email",value = "教师邮箱",required = true,example = "19546235486@qq.com")
            String email,
            @ApiParam(name = "telNumber",value = "教师联系电话",required = true,example = "17885622586")
            String telNumber){
        studentService.updateInfo(id,sex,email,telNumber);
        return JSONResult.ok();
    }

    @ApiOperation(value = "重新设置学生密码",notes = "重新设置学生密码",httpMethod = "POST")
    @PostMapping("resetPassword")
    @ResponseBody
    public JSONResult resetPassword(
            @ApiParam(name = "id",value = "学生id",required = true,example = "1212044344639040")
            String id,
            @ApiParam(name = "oldPass",value = "旧密码",required = true,example = "123456")
            String oldPass,
            @ApiParam(name = "newPass",value = "新密码",required = true,example = "1234567")
            String newPass,
            @ApiParam(name = "rePass",value = "确认新密码",required = true,example = "123456")
            String rePass){
        //1.确认输入新密码两次是否一致
        if(!newPass.equals(rePass)){
            return JSONResult.errorMsg("两次输入的密码不一致！");
        }
        //2.确认旧密码是否正确
        if(!studentService.confirmOldPassword(id,oldPass)){
            return JSONResult.errorMsg("旧密码输入错误！");
        }
        //3.更新密码

        return JSONResult.ok();
    }

}
