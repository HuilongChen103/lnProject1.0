package com.trainingmanagesys.web.course.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.course.entity.Course;
import com.trainingmanagesys.web.course.service.ICourseService;
import com.trainingmanagesys.web.course.vo.CourseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoying
 * @since 2020-05-18
 */
@Api(value = "课程审核", tags = {"课程审核接口"})
@RestController
@RequestMapping("/course")
@Validated
public class CourseController {

    @Autowired
    ICourseService courseService;

    @ApiOperation(value = "添加课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseCode", value = "课程编号",  dataType = "String", required = true),
            @ApiImplicitParam(name = "courseName", value = "课程名称",  dataType = "String", required = false),
            @ApiImplicitParam(name = "duration", value = "课程时长",  dataType = "String", required = false),
            @ApiImplicitParam(name = "studentMax", value = "最大学生数量",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "type", value = "类型",  dataType = "String", required = false),
            @ApiImplicitParam(name = "comment", value = "备注",  dataType = "String", required = false)
    })
    @PostMapping("/addCourse")
    @Transactional(rollbackFor = Exception.class)
    public String addCourse(@RequestBody @Validated Course course){
        return courseService.addCourse(course);
    }

    @ApiOperation(value = "修改课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseCode", value = "课程编号",  dataType = "String", required = true),
            @ApiImplicitParam(name = "courseName", value = "课程名称",  dataType = "String", required = false),
            @ApiImplicitParam(name = "duration", value = "课程时长",  dataType = "String", required = false),
            @ApiImplicitParam(name = "studentMax", value = "最大学生数量",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "type", value = "类型",  dataType = "String", required = false),
            @ApiImplicitParam(name = "comment", value = "备注",  dataType = "String", required = false)
    })
    @PostMapping("/updateCourse")
    @Transactional(rollbackFor = Exception.class)
    public String updateCourse(@RequestBody @Validated Course course){
        return courseService.updateCourse(course);
    }

    @ApiOperation(value = "删除课程")
    @ApiImplicitParam(name = "courseCode", value = "课程编号",  dataType = "String", required = true)
    @DeleteMapping("/deleteCourse")
    @Transactional(rollbackFor = Exception.class)
    public String deleteCourse(@NotNull(message = "请指明课程编号") String courseCode){
        return courseService.deleteCourse(courseCode);
    }

    @ApiOperation(value = "获取课程")
    @ApiImplicitParam(name = "courseCode", value = "课程编号",  dataType = "String", required = true)
    @DeleteMapping("/getCourse")
    public Course getCourse(@NotNull(message = "请指明课程编号") String courseCode){
        return courseService.getCourse(courseCode);
    }

    @ApiOperation(value = "列课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseName", value = "课程名称",  dataType = "String", required = false),
            @ApiImplicitParam(name = "studentMaxMax", value = "最大学生数量的上限",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "studentMaxMin", value = "最大学生数量的下限",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "type", value = "类型",  dataType = "String", required = false),
            @ApiImplicitParam(name = "limit", value = "数量",  dataType = "Integer", required = false)
    })
    @PostMapping("/listCourse")
    public List<Course> listCourse(@RequestBody @Validated CourseVO courseVO){
        return courseService.listCourse(courseVO);
    }

    @ApiOperation(value = "分页列课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseName", value = "课程名称",  dataType = "String", required = false),
            @ApiImplicitParam(name = "studentMaxMax", value = "最大学生数量的上限",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "studentMaxMin", value = "最大学生数量的下限",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "type", value = "类型",  dataType = "String", required = false),
            @ApiImplicitParam(name = "limit", value = "数量",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面",  dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面大小",  dataType = "Integer", required = true),
    })
    @PostMapping("/pagedListCourse")
    public IPage<Course> pagedListCourse(@RequestBody @Validated CourseVO courseVO){
        return courseService.pagedListCourse(courseVO);
    }
}
