package com.trainingmanagesys.web.teacher.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.clazz.entity.Clazz;
import com.trainingmanagesys.web.course.entity.Course;
import com.trainingmanagesys.web.teacher.entity.Teacourse;
import com.trainingmanagesys.web.teacher.service.ITeacourseService;
import com.trainingmanagesys.web.teacher.vo.TeacourseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2020-05-13
 */
@Api(value = "教师课程", tags = {"教师课程操作接口"})
@RestController
@RequestMapping("/teacourse")
@Validated
public class TeacourseController {

    @Autowired
    ITeacourseService teacourseService;

    @ApiOperation(value = "添加教师课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherId", value = "教师id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "courseCode", value = "课程编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "classCode", value = "班级编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "percentage", value = "班级表现(学生均分/满分)", dataType = "Long", required = false),
            @ApiImplicitParam(name = "remain", value = "学生留存率(实际学生数量/初始学生数量)", dataType = "Long", required = false),
            @ApiImplicitParam(name = "intro", value = "教师个人介绍，课程介绍。", dataType = "String", required = false)
    })
    @PostMapping("/addTeacourse")
    public String addTeaCourse(@RequestBody Teacourse teacourse){
        return teacourseService.addTeaCourse(teacourse);
    }

    @ApiOperation(value = "更新教师课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tcSerial", value = "teacher course流水号", dataType = "Long", required = true),
            @ApiImplicitParam(name = "teacherId", value = "教师id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "courseCode", value = "课程编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "classCode", value = "班级编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "percentage", value = "班级表现(学生均分/满分)", dataType = "Long", required = false),
            @ApiImplicitParam(name = "remain", value = "学生留存率(实际学生数量/初始学生数量)", dataType = "Long", required = false),
            @ApiImplicitParam(name = "intro", value = "教师个人介绍，课程介绍。", dataType = "String", required = false)
    })
    @PostMapping("/updateTeacourse")
    public String updateTeaCourse(@RequestBody @Validated Teacourse teacourse){
        return teacourseService.updateTeaCourse(teacourse);
    }

    @ApiOperation(value = "删除教师课程")
    @ApiImplicitParam(name = "tc_serial", value = "teacher course流水号", dataType = "Long", required = true)
    @DeleteMapping("/deleteTeacourse")
    public String deleteTeacourse(@NotNull(message = "请指明教师课程流水号") Long tcSerial){
        return teacourseService.deleteTeaCourse(tcSerial);
    }

    @ApiOperation(value = "获得教师课程")
    @ApiImplicitParam(name = "tc_serial", value = "teacher course流水号", dataType = "Long", required = true)
    @DeleteMapping("/getTeacourse")
    public Teacourse getTeacourse(@NotNull(message = "请指明教师课程流水号") Long tcSerial){
        return teacourseService.getTeaCourse(tcSerial);
    }

    @ApiOperation(value = "列教师课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherId", value = "教师id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "courseCode", value = "课程编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "classCode", value = "班级编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "percentageMax", value = "班级表现(学生均分/满分)最大值", dataType = "Long", required = false),
            @ApiImplicitParam(name = "percentageMin", value = "班级表现(学生均分/满分)最小值", dataType = "Long", required = false),
            @ApiImplicitParam(name = "remainMax", value = "学生留存率(实际学生数量/初始学生数量)最大值", dataType = "Long", required = false),
            @ApiImplicitParam(name = "remainMin", value = "学生留存率(实际学生数量/初始学生数量)最小值", dataType = "Long", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false)
    })
    @PostMapping("/listTeacourse")
    public List<Teacourse> listTeaCourse(@RequestBody TeacourseVO teacourseVO){
        return teacourseService.listTeaCourse(teacourseVO);
    }

    @ApiOperation(value = "分页列教师课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherId", value = "教师id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "courseCode", value = "课程编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "classCode", value = "班级编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "percentageMax", value = "班级表现(学生均分/满分)最大值", dataType = "Long", required = false),
            @ApiImplicitParam(name = "percentageMin", value = "班级表现(学生均分/满分)最小值", dataType = "Long", required = false),
            @ApiImplicitParam(name = "remainMax", value = "学生留存率(实际学生数量/初始学生数量)最大值", dataType = "Long", required = false),
            @ApiImplicitParam(name = "remainMin", value = "学生留存率(实际学生数量/初始学生数量)最小值", dataType = "Long", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面数量", dataType = "Integer", required = true)
    })
    @PostMapping("/pagedListTeacourse")
    public IPage<Teacourse> pagedListTeaCourse(@RequestBody @Validated(TeacourseVO.listKeyGroup.class) TeacourseVO teacourseVO){
        return teacourseService.pagedListTeaCourse(teacourseVO);
    }

    @ApiOperation(value = "根据老师ID列他/她选取的课程")
    @ApiImplicitParam(name = "teacherId", value = "老师id", dataType = "Long", required = true)
    @PostMapping("/listCourseByTeacherID")
    public List<Course> listCourseByTeacherID(@NotNull(message = "请指明老师ID") Long teacherId){
        return teacourseService.listCourseByTeacherID(teacherId);
    }

    @ApiOperation(value = "根据老师ID列他/她选取的课程")
    @ApiImplicitParam(name = "teacherId", value = "老师id", dataType = "Long", required = true)
    @PostMapping("/listClazzByTeacherID")
    public List<Clazz> listClazzByTeacherID(@NotNull(message = "请指明老师ID") Long teacherId){
        return teacourseService.listClazzByTeacherID(teacherId);
    }
}
