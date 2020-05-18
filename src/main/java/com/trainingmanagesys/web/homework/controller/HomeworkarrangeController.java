package com.trainingmanagesys.web.homework.controller;


import com.trainingmanagesys.web.homework.entity.Homeworkarrange;
import com.trainingmanagesys.web.homework.service.IHomeworkarrangeService;
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
 * @since 2020-05-18
 */
@Api(value = "作业安排", tags = {"作业安排操作接口"})
@RestController
@RequestMapping("/homework")
public class HomeworkarrangeController {

    @Autowired
    IHomeworkarrangeService homeworkarrangeService;

    @ApiOperation(value = "添加作业安排", notes = "添加作业安排")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classCode", value = "班级编号", dataType = "Long", required = false),
            @ApiImplicitParam(name = "arrangeFile", value = "相关文件 可为null", dataType = "Long", required = false),
            @ApiImplicitParam(name = "content", value = "作业内容文字说明", dataType = "String", required = false),
            @ApiImplicitParam(name = "deadline", value = "截止时间", dataType = "Date", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addHomeworkarrange")
    public Long addHomework(@RequestBody @Validated Homeworkarrange homeworkarrange){
        return homeworkarrangeService.addHomeworkarrange(homeworkarrange);
    }

    @ApiOperation(value = "更新作业安排", notes = "更新作业安排")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "arrangeSerial", value = "作业安排流水号", dataType = "Long", required = true),
            @ApiImplicitParam(name = "classCode", value = "班级编号", dataType = "Long", required = false),
            @ApiImplicitParam(name = "arrangeFile", value = "相关文件 可为null", dataType = "Long", required = false),
            @ApiImplicitParam(name = "content", value = "作业内容文字说明", dataType = "String", required = false),
            @ApiImplicitParam(name = "deadline", value = "截止时间", dataType = "Date", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/updateHomeworkarrange")
    public String updateHomeworkarrange(@RequestBody @Validated Homeworkarrange homeworkarrange){
        return homeworkarrangeService.updateHomeworkarrange(homeworkarrange);
    }

    @ApiOperation(value = "删除作业安排", notes = "删除作业安排")
    @ApiImplicitParam(name = "arrangeSerial", value = "作业安排流水号", dataType = "Long", required = true)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/deleteHomeworkarrange")
    public String deleteHomeworkarrange(@NotNull(message = "请指明作业安排流水号") Long arrangeSerial){
        return homeworkarrangeService.deleteHomeworkarrange(arrangeSerial);
    }

    @ApiOperation(value = "获取作业安排", notes = "获取作业安排")
    @ApiImplicitParam(name = "arrangeSerial", value = "作业安排流水号", dataType = "Long", required = true)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/getHomeworkarrange")
    public Homeworkarrange getHomeworkarrange(@NotNull(message = "请指明作业安排流水号") Long arrangeSerial){
        return homeworkarrangeService.getHomeworkarrange(arrangeSerial);
    }

}
