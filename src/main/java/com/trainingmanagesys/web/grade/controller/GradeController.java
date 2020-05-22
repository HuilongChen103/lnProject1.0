package com.trainingmanagesys.web.grade.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.grade.entity.Grade;
import com.trainingmanagesys.web.grade.service.IGradeService;
import com.trainingmanagesys.web.grade.vo.GradeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
@Api(value = "成绩", tags = {"成绩操作接口"})
@RestController
@RequestMapping("/grade")
@Validated
public class GradeController {

    @Autowired
    IGradeService gradeService;

    @ApiOperation(value = "添加成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testSerial", value = "考试号", dataType = "Long", required = true),
            @ApiImplicitParam(name = "studentId", value = "学生id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "grade", value = "分数", dataType = "Long", required = false),
            @ApiImplicitParam(name = "comment", value = "备注)", dataType = "String", required = false)
    })
    @PostMapping("/addGrade")
    public Long addGrade(@RequestBody @Validated Grade grade){
        return gradeService.addGrade(grade);
    }

    @ApiOperation(value = "修改成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gradeSerial", value = "分数流水号", dataType = "Long", required = true),
            @ApiImplicitParam(name = "testSerial", value = "考试号", dataType = "Long", required = true),
            @ApiImplicitParam(name = "studentId", value = "学生id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "grade", value = "分数", dataType = "Long", required = false),
            @ApiImplicitParam(name = "comment", value = "备注)", dataType = "String", required = false)
    })
    @PostMapping("/updateGrade")
    public String updateGrade(@RequestBody @Validated Grade grade){
        return gradeService.updateGrade(grade);
    }

    @ApiOperation(value = "删除成绩")
    @ApiImplicitParam(name = "gradeSerial", value = "分数流水号", dataType = "Long", required = true)
    @PostMapping("/deleteGrade")
    public String deleteGrade(@NotNull(message = "请输入分数流水号") Long gradeSerial){
        return gradeService.deleteGrade(gradeSerial);
    }

    @ApiOperation(value = "获取成绩")
    @ApiImplicitParam(name = "gradeSerial", value = "分数流水号", dataType = "Long", required = true)
    @PostMapping("/getGrade")
    public Grade getGrade(@NotNull(message = "请输入分数流水号") Long gradeSerial){
        return gradeService.getGrade(gradeSerial);
    }

    @ApiOperation(value = "列成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testSerial", value = "考试号", dataType = "Long", required = false),
            @ApiImplicitParam(name = "studentId", value = "学生id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false)
    })
    @PostMapping("/listGrade")
    public List<Grade> listGrade(@RequestBody @Validated GradeVO grade){
        return gradeService.listGrade(grade);
    }

    @ApiOperation(value = "分页列成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testSerial", value = "考试号", dataType = "Long", required = false),
            @ApiImplicitParam(name = "studentId", value = "学生id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面容量", dataType = "Integer", required = true)
    })
    @PostMapping("/pagedListGrade")
    public IPage<Grade> pagedListGrade(@RequestBody @Validated GradeVO grade){
        return gradeService.pagedListGrade(grade);
    }
}
