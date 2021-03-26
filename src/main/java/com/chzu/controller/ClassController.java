package com.chzu.controller;

import com.chzu.model.TbClass;
import com.chzu.model.vo.TransferData;
import com.chzu.service.ClassService;
import com.chzu.util.JSONResult;
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

/**
 * @author hyy
 */
@Api(value = "班级",tags = "与班级相关的接口")
@Controller
@RequestMapping("class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @ApiOperation(value = "保存班级信息",notes = "保存班级信息",httpMethod = "POST")
    @PostMapping("save")
    @ResponseBody
    public JSONResult save(String claName){
        classService.save(claName);
        return JSONResult.ok();

    }

    @ApiOperation(value = "查询所有班级信息",notes = "查询所有班级信息",httpMethod = "GET")
    @GetMapping("getAll")
    @ResponseBody
    public JSONResult getAll(){
        List<TbClass> classList = classService.getAll();
        if(classList.size() > 0){
            return JSONResult.ok(classList);
        }else{
            return JSONResult.errorMsg("还未添加任何班级");
        }
    }

    /**
     * 得到所有班级信息，按照page 和 limit 分页，返回到前端
     * @param page
     * @param limit
     * @return
     */
   /* @RequestMapping("getList")
    @ResponseBody
    public TableDate getList(int page,int limit){
        List<ClassEntity> classEntities = classService.getList(page,limit);
        if(classEntities.size() > 0){
            return TableDate.ok(classEntities,classEntities.size());
        }else{
            return TableDate.error("无相关数据");
        }
    }*/

    /**
     *
     * @param claName 班级名称
     * @return 按照班级名称查询班级信息
     */
    /*@RequestMapping("getByClaName")
    @ResponseBody
    public TableDate getByClaName(String claName){
        List<ClassEntity> classList = classService.getByClaName(claName);
        if(classList.size() > 0){
            return TableDate.ok(classList,classList.size());
        }else{
            return TableDate.error("无相关数据");
        }
    }*/

    /**
     *
     * @param teaId 教师的id
     * @return 返回从数据数查询的班级信息，班级信息按与该教师是否建立关系为准分类
     */
    @ApiOperation(value = "查询所有班级信息",notes = "班级信息按与该教师是否建立关系为准分类",httpMethod = "POST")
    @PostMapping("getClassInfoByTeaId")
    @ResponseBody
    public JSONResult getClassInfoByTeaId(
            @ApiParam(name = "teaId",value = "教师id",required = true,example = "0123030309347781")
            String teaId){
        List<TransferData> dataList = classService.getClassInfoByTeaId(teaId);
        if(dataList.size() > 0){
            return JSONResult.ok(dataList);
        }else{
            return JSONResult.errorMsg("无未建立关系的班级");
        }
    }

}
