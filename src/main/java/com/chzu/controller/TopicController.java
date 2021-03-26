package com.chzu.controller;

import com.chzu.model.TbTopic;
import com.chzu.service.TopicService;
import com.chzu.util.JSONResult;
import com.chzu.util.TableDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(value = "课题",tags = "与课题相关的接口")
@Controller
@RequestMapping("topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @ApiOperation(value = "保存选题信息",notes = "保存选题信息",httpMethod = "POST")
    @PostMapping("addTopic")
    @ResponseBody
    public JSONResult addTopic(
            @ApiParam(name = "topic",value = "课题名称",required = true)
            String topic,
            @ApiParam(name = "description",value = "课题内容及要求",required = true)
            String description,
            @ApiParam(name = "teaId",value = "教师id",required = true,example = "1212121212")
            String teaId){
        topicService.addTopic(topic,description,teaId);
        return JSONResult.ok();
    }

    @ApiOperation(value = "查询老师上传的选题信息",notes = "查询老师上传的选题信息",httpMethod = "GET")
    @GetMapping("queryAllByTeaId")
    @ResponseBody
    public TableDate queryAllByTeaId(
            @ApiParam(name = "teaId",value = "教师id",required = true,example = "1212121212")
            String teaId){
        List<TbTopic> topicList = topicService.queryAllByTeaId(teaId);
        return TableDate.ok(topicList,topicList.size());
    }


    @ApiOperation(value = "根据学生id查询老师上传的选题信息",notes = "根据学生id查询老师上传的选题信息",httpMethod = "GET")
    @GetMapping("queryAllByStuId")
    @ResponseBody
    public TableDate queryAllByStuId(
            @ApiParam(name = "stuId",value = "学生id",required = true,example = "1212121212")
            String stuId){
        List<TbTopic> topicList = topicService.queryAllByStuId(stuId);
        return TableDate.ok(topicList,topicList.size());
    }

    @ApiOperation(value = "学生选择选题",notes = "学生选择选题",httpMethod = "POST")
    @PostMapping("chooseTopic")
    @ResponseBody
    public JSONResult chooseTopic(
            @ApiParam(name = "stuId",value = "学生id",required = true,example = "1212121212")
            String stuId,
            @ApiParam(name = "topicId",value = "课题id",required = true,example = "1212121212")
            String topicId){
        String result = topicService.chooseTopic(stuId,topicId);
        if(result.equals("success"))
            return JSONResult.ok();
        else return JSONResult.errorMsg(result);
    }

    @ApiOperation(value = "根据学生id查询课题信息",notes = "根据学生id查询课题信息",httpMethod = "POST")
    @PostMapping("queryInfoByStuId")
    @ResponseBody
    public JSONResult queryInfoByStuId(
            @ApiParam(name = "stuId",value = "学生id",required = true,example = "1212121212")
            String stuId){
        TbTopic topic = topicService.queryByStuId(stuId);
        if(topic == null)
            return JSONResult.errorMsg("该学生团队未选择课题。");
        else
            return JSONResult.ok(topic);
    }

    @ApiOperation(value = "删除选题信息",notes = "删除选题信息",httpMethod = "POST")
    @PostMapping("deleteById")
    @ResponseBody
    public JSONResult deleteById(String id){
        topicService.deleteById(id);
        return JSONResult.ok();
    }
}
