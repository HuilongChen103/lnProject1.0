package com.trainingmanagesys.web.clazz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.clazz.entity.Clazz;
import com.trainingmanagesys.web.clazz.service.IClazzService;
import com.trainingmanagesys.web.clazz.vo.ClazzVO;
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
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
@Api(value = "班级审核", tags = {"班级审核接口"})
@RestController
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    IClazzService clazzService;

    @ApiOperation(value = "添加班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseCode", value = "课程号",  dataType = "String", required = false),
            @ApiImplicitParam(name = "studentNum", value = "起始学生数量(开学的时候)",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "realNum", value = "实际学生数量",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "teacherId", value = "教师id",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "scheduleSerial", value = "日程安排编号",  dataType = "Long", required = false)
    })
    @PostMapping("/addClazz")
    @Transactional(rollbackFor = Exception.class)
    public String addClazz(@RequestBody @Validated({Clazz.addKeyGroup.class, Clazz.addAdditionGroup.class}) Clazz clazz){
        return clazzService.addClazz(clazz);
    }

    @ApiOperation(value = "更新班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classCode", value = "班级号",  dataType = "String", required = true),
            @ApiImplicitParam(name = "courseCode", value = "课程号",  dataType = "String", required = false),
            @ApiImplicitParam(name = "studentNum", value = "起始学生数量(开学的时候)",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "realNum", value = "实际学生数量",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "teacherId", value = "教师id",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "scheduleSerial", value = "日程安排编号",  dataType = "Long", required = false)
    })
    @PostMapping("/updateClazz")
    @Transactional(rollbackFor = Exception.class)
    public String updateClazz(@RequestBody @Validated Clazz clazz){
        return clazzService.updateClazz(clazz);
    }

    @ApiOperation(value = "删除班级")
    @ApiImplicitParam(name = "classCode", value = "班级号",  dataType = "String", required = true)
    @PostMapping("/deleteClazz")
    @Transactional(rollbackFor = Exception.class)
    public String deleteClazz(@NotNull(message = "请输入班级号") String classCode){
        return clazzService.deleteClazz(classCode);
    }

    @ApiOperation(value = "获取班级")
    @ApiImplicitParam(name = "classCode", value = "班级号",  dataType = "String", required = true)
    @PostMapping("/getClazz")
    public Clazz getClazz(@NotNull(message = "请输入班级号") String classCode){
        return clazzService.getClazz(classCode);
    }

    @ApiOperation(value = "列班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseCode", value = "课程号",  dataType = "String", required = false),
            @ApiImplicitParam(name = "teacherId", value = "教师id",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "limit", value = "数量",  dataType = "Integer", required = false)
    })
    @PostMapping("/listClazz")
    public List<Clazz> listClazz(@RequestBody ClazzVO vo){
        return clazzService.listClazz(vo);
    }

    @ApiOperation(value = "分页列班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseCode", value = "课程号",  dataType = "String", required = false),
            @ApiImplicitParam(name = "teacherId", value = "教师id",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "limit", value = "数量",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面",  dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面数量",  dataType = "Integer", required = true)
    })
    @PostMapping("/pagedListClazz")
    public IPage<Clazz> pagedListClazz(@RequestBody @Validated(ClazzVO.listKeyGroup.class) ClazzVO vo){
        return clazzService.pagedListClazz(vo);
    }
}
