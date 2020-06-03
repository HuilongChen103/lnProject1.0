package com.trainingmanagesys.web.clazz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.clazz.entity.Clazz;
import com.trainingmanagesys.web.clazz.service.IClazzService;
import com.trainingmanagesys.web.clazz.vo.ClazzVO;
import com.trainingmanagesys.web.clazz.vo.ReturnedListClazzVO;
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
// 用于swagger文档，说明这个接口的标题和主要功能
@Api(value = "班级审核", tags = {"班级审核接口"})
// 结合@Controller（声明这是一个controller层）和@RequestBody（用于接收前端传递给后端的json数据）
@RestController
// 说明要映射的url，以便后端处理
@RequestMapping("/clazz")
public class ClazzController {

    // 自动装配clazzService，避免多次创建clazzService，让他们都用这同一个，节省内存，提升效率
    @Autowired
    IClazzService clazzService;

    // swagger文档说明这个方法的名字
    @ApiOperation(value = "添加班级")
    // swagger文档说明变量
    @ApiImplicitParams({
            // swagger文档声明变量名字、用途、类型、是否必传
            @ApiImplicitParam(name = "courseCode", value = "课程号",  dataType = "String", required = false),
            @ApiImplicitParam(name = "studentNum", value = "起始学生数量(开学的时候)",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "realNum", value = "实际学生数量",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "studentMax", value = "最大学生数量",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "teacherId", value = "教师id",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "roomNum", value = "房间号",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "scheduleSerial", value = "日程安排编号",  dataType = "Long", required = false)
    })
    // 用于接口映射
    @PostMapping("/addClazz")
    // 事件回滚，如果方法执行的时候报错（比如这里是发生了Exception类的异常），就会滚这次操作
    // 比如添加课程到一半的时候出错了，就取消这次添加，使其回到添加前的状态，避免存入错误的数据
    @Transactional(rollbackFor = Exception.class)
    // @RequestBody声明传入json格式数据，并用Clazz类来接收
    // @Validated用于声明要使用的校验组
    public String addClazz(@RequestBody @Validated({Clazz.addKeyGroup.class, Clazz.addAdditionGroup.class}) Clazz clazz){
        return clazzService.addClazz(clazz);
    }

    @ApiOperation(value = "更新班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classCode", value = "班级号",  dataType = "String", required = true),
            @ApiImplicitParam(name = "courseCode", value = "课程号",  dataType = "String", required = false),
            @ApiImplicitParam(name = "studentNum", value = "起始学生数量(开学的时候)",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "realNum", value = "实际学生数量",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "studentMax", value = "最大学生数量",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "teacherId", value = "教师id",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "roomNum", value = "房间号",  dataType = "Long", required = false),
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classCode", value = "班级号",  dataType = "String", required = true),
            @ApiImplicitParam(name = "enable", value = "enable",  dataType = "Integer", required = true)
    })
    @PostMapping("/getClazz")
    public Clazz getClazz(@RequestBody Clazz clazz){
        String classCode = clazz.getClassCode();
        Integer enable = clazz.getEnable();
        System.out.println(classCode);
        System.out.println(enable);
        return clazzService.getClazz(classCode, enable);
    }

    @ApiOperation(value = "列班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseCode", value = "课程号",  dataType = "String", required = false),
            @ApiImplicitParam(name = "courseName", value = "课程名称",  dataType = "String", required = false),
            @ApiImplicitParam(name = "teacherId", value = "教师id",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "roomNum", value = "房间号",  dataType = "Long", required = false)
    })
    @PostMapping("/listClazz")
    public List<ReturnedListClazzVO> listClazz(@RequestBody ClazzVO vo){
        return clazzService.listClazz(vo);
    }

    @ApiOperation(value = "分页列班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseCode", value = "课程号",  dataType = "String", required = false),
            @ApiImplicitParam(name = "courseName", value = "课程名称",  dataType = "String", required = false),
            @ApiImplicitParam(name = "teacherId", value = "教师id",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面",  dataType = "Integer", required = true),
            @ApiImplicitParam(name = "roomNum", value = "房间号",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "pageSize", value = "页面数量",  dataType = "Integer", required = true)
    })
    @PostMapping("/pagedListClazz")
    public IPage<ReturnedListClazzVO> pagedListClazz(@RequestBody @Validated(ClazzVO.listKeyGroup.class) ClazzVO vo){
        return clazzService.pagedListClazz(vo);
    }
}
