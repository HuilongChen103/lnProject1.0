package com.trainingmanagesys.web.homework.controller;


import com.trainingmanagesys.web.homework.entity.Homework;
import com.trainingmanagesys.web.homework.service.IHomeworkService;
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
 * @since 2020-05-13
 */
@Api(value = "作业", tags = {"作业操作接口"})
@RestController
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    IHomeworkService homeworkService;

    @ApiOperation(value = "添加作业", notes = "添加作业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "arrangeSerial", value = "作业安排流水号", dataType = "Long", required = false),
            @ApiImplicitParam(name = "studentId", value = "学生id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "hwFile", value = "作业文件", dataType = "Long", required = false),
            @ApiImplicitParam(name = "grade", value = "分数", dataType = "String", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addHomework")
    public Long addHomework(@RequestBody @Validated Homework homework){
        return homeworkService.addHomework(homework);
    }

    @ApiOperation(value = "更新作业", notes = "更新作业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hwSerial", value = "作业流水号", dataType = "Long", required = true),
            @ApiImplicitParam(name = "arrangeSerial", value = "作业安排流水号", dataType = "Long", required = false),
            @ApiImplicitParam(name = "studentId", value = "学生id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "hwFile", value = "作业文件", dataType = "Long", required = false),
            @ApiImplicitParam(name = "grade", value = "分数", dataType = "String", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/updateHomework")
    public String updateHomework(@RequestBody @Validated Homework homework){
        return homeworkService.updateHomework(homework);
    }

    @ApiOperation(value = "删除作业", notes = "删除作业")
    @ApiImplicitParam(name = "hwSerial", value = "作业流水号", dataType = "Long", required = true)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/deleteHomework")
    public String deleteHomework(@NotNull(message = "请指明作业流水号") Long hwSerial){
        return homeworkService.deleteHomework(hwSerial);
    }

    @ApiOperation(value = "获取作业", notes = "获取作业")
    @ApiImplicitParam(name = "hwSerial", value = "作业流水号", dataType = "Long", required = true)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/getHomework")
    public Homework getHomework(@NotNull(message = "请指明作业流水号") Long hwSerial){
        return homeworkService.getHomework(hwSerial);
    }
}
