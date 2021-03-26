package com.chzu.controller;

import com.chzu.enums.UserType;
import com.chzu.model.TbAdmin;
import com.chzu.model.TbStudent;
import com.chzu.model.TbTeacher;
import com.chzu.model.bo.UserInfo;
import com.chzu.service.AdminService;
import com.chzu.service.StudentService;
import com.chzu.service.TeacherService;
import com.chzu.util.Constant;
import com.chzu.util.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Api(value = "登录登出等操作",tags = "登录登出等操作")
@Controller
public class LoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "登录接口",notes = "登录接口",httpMethod = "POST")
    @PostMapping("login")
    @ResponseBody
    public JSONResult login(
            @ApiParam(name = "username",value = "用户名",required = true,example = "2017211685") String username,
            @ApiParam(name = "password",value = "密码",required = true,example = "123456") String password,
            @ApiParam(name = "checkCode",value = "验证码",required = true,example = "qz04") String checkCode,
            @ApiParam(name = "type",value = "类型",required = true,example = "1") int type,
            @ApiParam(name = "session",value = "session对象",required = true,example = "session")HttpSession session)throws Exception{
        //验证码验证
        String identifyCode = (String) session.getAttribute("IdentifyCode");
        if(checkCode.equals(identifyCode) != true){
            return JSONResult.errorMsg("验证码错误");
        }

        UserInfo user = new UserInfo();
        // 1  教师登录
        if(type == 0){
            TbTeacher teacher = null;
            //用户是否存在
            if(teacherService.accountIsExisted(username) == false){
                return JSONResult.errorMsg("账号不存在");
            }

            //账号密码验证
            teacher = teacherService.getByAccountAndPassword(username,password);
            if(teacher == null){
                return JSONResult.errorMsg("密码错误");
            }
            //将相关信息存至session
            user.setId(teacher.getId());
            user.setUsername(teacher.getName());
            user.setType(UserType.teacher.type);
            user.setInfo(teacher);
            session.setAttribute("clientType","teacher");
            session.setAttribute("clientId",teacher.getId());
            session.setAttribute("clientName",teacher.getName());
            //登录成功
            return JSONResult.ok(user);

        // 2  学生登录
        }else if(type == 1){
            //用户是否存在
            if(studentService.accountIsExisted(username) == false){
                return JSONResult.errorMsg("账号不存在");
            }
            //账号密码验证
            TbStudent student = studentService.queryByAccountAndPassword(username,password);
            if(student == null){
                return JSONResult.errorMsg("密码错误");
            }

            //将相关信息存至session
            user.setId(student.getId());
            user.setUsername(student.getName());
            user.setType(UserType.student.type);
            user.setInfo(student);
            session.setAttribute("clientType","student");
            session.setAttribute("clientId",student.getId());
            session.setAttribute("clientName",student.getName());
            //登录成功
            return JSONResult.ok(user);
        // 3  管理员登录
        }else{
            //账号是否存在
            if(adminService.accountIsExisted(username) == false){
                return JSONResult.errorMsg("账号不存在");
            }

            //账号密码验证
            TbAdmin admin = adminService.queryByAccountAndPassword(username,password);
            if(admin == null){
                return JSONResult.errorMsg("密码错误");
            }

            //相关信息存至session

            //登录成功信息存至前端
            user.setId(admin.getId());
            user.setType(UserType.admin.type);
            user.setUsername("admin");
            return JSONResult.ok(user);
        }
    }

    @ApiOperation(value = "退出接口",notes = "退出接口",httpMethod = "POST")
    @PostMapping("logout")
    @ResponseBody
    public JSONResult logout(String id){
        if (Constant.onlineUsers.containsKey(id)) {
            Constant.onlineUsers.remove(id);
        }
        return JSONResult.ok();
    }


}
