package com.trainingmanagesys.web.homework.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.homework.entity.Homework;
import com.trainingmanagesys.web.homework.service.IHomeworkService;
import com.trainingmanagesys.web.homework.vo.HomeworkVO;
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
            @ApiImplicitParam(name = "grade", value = "分数", dataType = "Long", required = false)
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
            @ApiImplicitParam(name = "grade", value = "分数", dataType = "Long", required = false)
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

    @ApiOperation(value = "列作业", notes = "列作业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "arrangeSerial", value = "作业安排流水号", dataType = "Long", required = false),
            @ApiImplicitParam(name = "studentId", value = "学生id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "hwFile", value = "作业文件", dataType = "Long", required = false),
            @ApiImplicitParam(name = "gradeMax", value = "最高分数", dataType = "Long", required = false),
            @ApiImplicitParam(name = "gradeMin", value = "最低分数", dataType = "Long", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/listHomework")
    public List<Homework> listHomework(@RequestBody HomeworkVO vo){
        return homeworkService.listHomework(vo);
    }

    @ApiOperation(value = "分页列作业", notes = "分页列作业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "arrangeSerial", value = "作业安排流水号", dataType = "Long", required = false),
            @ApiImplicitParam(name = "studentId", value = "学生id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "hwFile", value = "作业文件", dataType = "Long", required = false),
            @ApiImplicitParam(name = "gradeMax", value = "最高分数", dataType = "Long", required = false),
            @ApiImplicitParam(name = "gradeMin", value = "最低分数", dataType = "Long", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面容量", dataType = "Integer", required = true)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/pagedListHomework")
    public IPage<Homework> pagedListHomework(@RequestBody @Validated(HomeworkVO.listKeyGroup.class) HomeworkVO vo){
        return homeworkService.pagedListHomework(vo);
    }
}
