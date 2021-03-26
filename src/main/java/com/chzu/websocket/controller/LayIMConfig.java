package com.chzu.websocket.controller;

import com.chzu.enums.UserType;
import com.chzu.service.AdminService;
import com.chzu.service.StudentService;
import com.chzu.service.TeacherService;
import com.chzu.util.ResponseData;
import com.chzu.util.layimInit.LayIMInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hyy
 * 描述：聊天小窗口的初始化
 */
@Controller
@RequestMapping("config")
public class LayIMConfig {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AdminService adminService;

    @PostMapping("init")
    @ResponseBody
    public ResponseData init(String id, int type){
        LayIMInit layIMInit ;
        if(type == UserType.student.type){
            layIMInit = studentService.init(id);
            System.out.println(layIMInit.getMine().getUsername());
            return ResponseData.ok(layIMInit);
        }else if(type == UserType.teacher.type){
            layIMInit = teacherService.init(id);
            System.out.println(layIMInit.getMine().getUsername());
            return ResponseData.ok(layIMInit);
        }else {
            layIMInit = adminService.init(id);
            return ResponseData.ok(layIMInit);
        }
    }

    @RequestMapping("getMemberByGroId")
    @ResponseBody
    public ResponseData getMemberByGroId(String id){
        return ResponseData.ok(null);
    }
}
