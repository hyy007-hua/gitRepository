package com.chzu.controller;

import com.chzu.service.GroupService;
import com.chzu.util.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "群组相关接口",tags = "群组相关接口")
@RestController
@RequestMapping("group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @ApiOperation(value = "随机分组",notes = "随机分组",httpMethod = "POST")
    @PostMapping("randomGrouping")
    public JSONResult divideGroup(
            @ApiParam(name = "classId",value = "班级id",required = true,example = "121312312312312")
            String classId,
            @ApiParam(name = "memberNumber",value = "每组人数",required = true,example = "5")
            int memberNumber){
        if(memberNumber <= 0){
            return JSONResult.errorMsg("分组人数输入错误！");
        }
        groupService.randomGrouping(classId,memberNumber);
        return JSONResult.ok();
    }

}
