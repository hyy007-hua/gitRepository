package com.chzu.controller;

import com.chzu.model.TbTeacher;
import com.chzu.model.vo.TeacherVO;
import com.chzu.model.vo.TreeData;
import com.chzu.service.TeacherService;
import com.chzu.util.JSONResult;
import com.chzu.util.TableDate;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hyy
 */
@Api(value = "教师",tags = "与教师相关的接口")
@Controller
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "保存教师信息",notes = "保存教师信息",httpMethod = "POST")
    @PostMapping("save")
    @ResponseBody
    public JSONResult save(TbTeacher teacher) {
        int result = teacherService.save(teacher);
        if(result == 1)
            return JSONResult.ok();
        else
            return JSONResult.errorMsg("操作失败！");
    }



    @ApiOperation(value = "查询所有教师信息",notes = "查询所有教师信息",httpMethod = "GET")
    @GetMapping("getAll")
    @ResponseBody
    public TableDate getAll(
            @ApiParam(name = "page",value = "页码",required = true,example = "1") int page,
            @ApiParam(name = "limit",value = "页面数",required = true,example = "10,20") int limit){
        PageInfo<TeacherVO> teacherList = teacherService.getAll(page,limit);
        return TableDate.ok(teacherList.getList(),teacherList.getSize());
    }



    /*@ApiOperation(value = "根据时间或教师编号查询教师信息",notes = "根据时间或教师编号查询教师信息",httpMethod = "POST")
    @PostMapping("getByTimeOrNumber")
    @ResponseBody
    public TableDate getByTimeOrNumber(String start,String end,String number) throws ParseException {
        List<TeacherEntity> teacherList = teacherService.getByTimeOrNumber(start,end,number,0);
        if(teacherList.size() > 0){
            return TableDate.ok(teacherList,teacherList.size());
        }else{
            return TableDate.error("无相关数据");
        }
    }*/

    @ApiOperation(value = "建立班级与教师的关系",notes = "建立班级与教师的关系",httpMethod = "POST")
    @PostMapping("linkTeacherAndClass")
    @ResponseBody
    public JSONResult linkTeacherAndClass(
            @ApiParam(name = "teaId",value = "教师id",required = true,example = "1212044344639040")
            @RequestParam String teaId,
            @ApiParam(name = "classIds",value = "班级id组字符串",required = true,example = "'1212121212,1212121212,'")
            @RequestParam String classIds){
        String[] ids = classIds.split(",");
        int result = teacherService.linkTeacherAndClass(teaId,ids);
        if(result == ids.length){
            return JSONResult.ok();
        }else{
            return JSONResult.errorMsg("操作失败");
        }
    }

    @ApiOperation(value = "删除班级与教师的关系",notes = "删除班级与教师的关系",httpMethod = "POST")
    @PostMapping("delLinkTeacherAndClass")
    @ResponseBody
    public JSONResult delLinkTeacherAndClass(
            @ApiParam(name = "teaId",value = "教师id",required = true,example = "1212044344639040")
            @RequestParam String teaId,
            @ApiParam(name = "classIds",value = "班级id组字符串",required = true,example = "'1212121212,1212121212,'")
            @RequestParam String classIds){
        String[] ids = classIds.split(",");
        int result = teacherService.delLinkTeacherAndClass(teaId,ids);
        if(result > 0){
            return JSONResult.ok();
        }else{
            return JSONResult.errorMsg("操作失败");
        }
    }

    @ApiOperation(value = "重新设置教师密码",notes = "重新设置教师密码",httpMethod = "POST")
    @PostMapping("resetPassword")
    @ResponseBody
    public JSONResult resetPassword(
            @ApiParam(name = "id",value = "教师id",required = true,example = "1212044344639040")
            String id,
            @ApiParam(name = "oldPass",value = "旧密码",required = true,example = "123456")
            String oldPass,
            @ApiParam(name = "newPass",value = "新密码",required = true,example = "1234567")
            String newPass,
            @ApiParam(name = "rePass",value = "确认新密码",required = true,example = "123456")
            String rePass) {
        //1.确认输入新密码两次是否一致
        if(!newPass.equals(rePass)){
            return JSONResult.errorMsg("两次输入的密码不一致！");
        }
        //2.确认旧密码是否正确
        if(teacherService.confirmOldPassword(id,oldPass) == false){
            return JSONResult.errorMsg("旧密码输入错误！");
        }
        //3.更改密码
        if(teacherService.resetPassword(id,newPass)){
            return JSONResult.ok();
        }else{
            return JSONResult.errorMsg("操作失败！");
        }
    }


    @ApiOperation(value = "更新教师个人信息",notes = "更新教师个人信息",httpMethod = "POST")
    @PostMapping("updateInfo")
    @ResponseBody
    public JSONResult updateInfo(
            @ApiParam(name = "number",value = "教师编号",required = true,example = "2020001")
            String number,
            @ApiParam(name = "sex",value = "教师性别",required = true,example = "1")
            int sex,
            @ApiParam(name = "email",value = "教师邮箱",required = true,example = "19546235486@qq.com")
            String email,
            @ApiParam(name = "telNumber",value = "教师联系电话",required = true,example = "17885622586")
            String telNumber){
        int result = teacherService.updateInfo(number,sex,email,telNumber);
        if(result > 0){
            return JSONResult.ok();
        }else{
            return JSONResult.errorMsg("操作失败");
        }
    }

    @ApiOperation(value = "查询大纲及课程审查表相关信息",notes = "查询大纲及课程审查表相关信息",httpMethod = "POST")
    @PostMapping("queryDataByTeaId")
    @ResponseBody
    public JSONResult queryDataByTeaId(
            @ApiParam(name = "teaId",value = "教师id",required = true,example = "1212044344639040")
            String teaId){
        List<TreeData> treeDataList = teacherService.queryDataByTeaId(teaId);
        return JSONResult.ok(treeDataList);
    }

}
