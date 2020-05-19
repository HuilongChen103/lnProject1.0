package com.trainingmanagesys.web.assessment.controller;


import com.trainingmanagesys.web.assessment.entity.Assessment;
import com.trainingmanagesys.web.assessment.service.IAssessmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
@Api(value = "测评", tags = {"测评接口"})
@RestController
@RequestMapping("/assessment")
@Validated
public class AssessmentController {

    @Autowired
    IAssessmentService assessmentService;

    @ApiOperation(value = "添加测评")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "targetId", value = "被测者id",  dataType = "Long", required = true),
            @ApiImplicitParam(name = "assessorId", value = "测评者id",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "semester", value = "课程时长学期",  dataType = "String", required = false),
            @ApiImplicitParam(name = "year", value = "学年",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "eventCode", value = "事件编号(班级，招聘，活动等)",  dataType = "String", required = false),
            @ApiImplicitParam(name = "grade", value = "分数",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "comment", value = "评价",  dataType = "String", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addAssessment")
    public Long addAssessment(@RequestBody @Validated Assessment assessment){
        return assessmentService.addAssessment(assessment);
    }

    @ApiOperation(value = "添加测评")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "assessSerial", value = "测评流水号",  dataType = "Long", required = true),
            @ApiImplicitParam(name = "targetId", value = "被测者id",  dataType = "Long", required = true),
            @ApiImplicitParam(name = "assessorId", value = "测评者id",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "semester", value = "课程时长学期",  dataType = "String", required = false),
            @ApiImplicitParam(name = "year", value = "学年",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "eventCode", value = "事件编号(班级，招聘，活动等)",  dataType = "String", required = false),
            @ApiImplicitParam(name = "grade", value = "分数",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "comment", value = "评价",  dataType = "String", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/updateAssessment")
    public String updateAssessment(@RequestBody @Validated Assessment assessment){
        return assessmentService.updateAssessment(assessment);
    }

    @ApiOperation(value = "删除测评")
    @ApiImplicitParam(name = "assessSerial", value = "测评流水号",  dataType = "Long", required = true)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/deleteAssessment")
    public String deleteAssessment(@RequestBody @Validated Long assessSerial){
        return assessmentService.deleteAssessment(assessSerial);
    }

    @ApiOperation(value = "获得测评")
    @ApiImplicitParam(name = "assessSerial", value = "测评流水号",  dataType = "Long", required = true)
    @PostMapping("/getAssessment")
    public Assessment getAssessment(@RequestBody @Validated Long assessSerial){
        return assessmentService.getAssessment(assessSerial);
    }
}
