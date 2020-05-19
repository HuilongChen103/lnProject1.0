package com.trainingmanagesys.web.course.controller;


import com.trainingmanagesys.web.course.entity.Courseware;
import com.trainingmanagesys.web.course.service.ICoursewareService;
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

import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
@Api(value = "课件审核", tags = {"课件审核接口"})
@RestController
@RequestMapping("/courseware")
@Validated
public class CoursewareController {

    @Autowired
    ICoursewareService coursewareService;

    @ApiOperation(value = "添加课件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classCode", value = "班级号",  dataType = "String", required = false),
            @ApiImplicitParam(name = "title", value = "标题",  dataType = "String", required = false),
            @ApiImplicitParam(name = "fileSerial", value = "文件序号",  dataType = "Long", required = false)
    })
    @PostMapping("/addCourseware")
    @Transactional(rollbackFor = Exception.class)
    public Long addCourseware(@RequestBody @Validated Courseware courseware){
        return coursewareService.addCourseware(courseware);
    }

    @ApiOperation(value = "更新课件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coursewareSerial", value = "课件流水号",  dataType = "Long", required = true),
            @ApiImplicitParam(name = "classCode", value = "班级号",  dataType = "String", required = false),
            @ApiImplicitParam(name = "title", value = "标题",  dataType = "String", required = false),
            @ApiImplicitParam(name = "fileSerial", value = "文件序号",  dataType = "Long", required = false)
    })
    @PostMapping("/updateCourseware")
    @Transactional(rollbackFor = Exception.class)
    public String updateCourseware(@RequestBody @Validated Courseware courseware){
        return coursewareService.updateCourseware(courseware);
    }

    @ApiOperation(value = "删除课件")
    @ApiImplicitParam(name = "coursewareSerial", value = "课件流水号",  dataType = "Long", required = true)
    @PostMapping("/deleteCourseware")
    @Transactional(rollbackFor = Exception.class)
    public String deleteCourseware(@NotNull(message = "请指明课件流水号") Long coursewareSerial){
        return coursewareService.deleteCourseware(coursewareSerial);
    }

    @ApiOperation(value = "获得课件")
    @ApiImplicitParam(name = "coursewareSerial", value = "课件流水号",  dataType = "Long", required = true)
    @PostMapping("/getCourseware")
    @Transactional(rollbackFor = Exception.class)
    public Courseware getCourseware(@NotNull(message = "请指明课件流水号") Long coursewareSerial){
        return coursewareService.getCourseware(coursewareSerial);
    }
}
