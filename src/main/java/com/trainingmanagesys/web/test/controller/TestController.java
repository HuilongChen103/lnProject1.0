package com.trainingmanagesys.web.test.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.test.entity.Test;
import com.trainingmanagesys.web.test.service.ITestService;
import com.trainingmanagesys.web.test.vo.TestVO;
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
@Api(value = "考试", tags = {"考试操作接口"})
@RestController
@RequestMapping("/test")
@Validated
public class TestController {

    @Autowired
    ITestService testService;

    @ApiOperation(value = "添加考试", notes = "添加考试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classCode", value = "班级号", dataType = "String", required = false),
            @ApiImplicitParam(name = "testerId1", value = "监考人甲", dataType = "Long", required = false),
            @ApiImplicitParam(name = "testerId2", value = "监考人乙", dataType = "Long", required = false),
            @ApiImplicitParam(name = "testFile", value = "考试内容文件", dataType = "Long", required = false),
            @ApiImplicitParam(name = "scheduleSerial", value = "日程安排流水号", dataType = "Long", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addTest")
    public Long addTest(@RequestBody @Validated Test test){
        return testService.addTest(test);
    }

    @ApiOperation(value = "更新考试", notes = "更新考试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testSerial", value = "考试号", dataType = "Long", required = true),
            @ApiImplicitParam(name = "classCode", value = "班级号", dataType = "String", required = false),
            @ApiImplicitParam(name = "testerId1", value = "监考人甲", dataType = "Long", required = false),
            @ApiImplicitParam(name = "testerId2", value = "监考人乙", dataType = "Long", required = false),
            @ApiImplicitParam(name = "testFile", value = "考试内容文件", dataType = "Long", required = false),
            @ApiImplicitParam(name = "scheduleSerial", value = "日程安排流水号", dataType = "Long", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/updateTest")
    public String updateTest(@RequestBody @Validated Test test){
        return testService.updateTest(test);
    }

    @ApiOperation(value = "删除考试")
    @ApiImplicitParam(name = "testSerial", value = "考试号", dataType = "Long", required = true)
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("/deleteTest")
    public String deleteTest(@NotNull(message = "请指明考试号") Long testSerial){
        return testService.deleteTest(testSerial);
    }

    @ApiOperation(value = "列考试", notes = "列考试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classCode", value = "班级号", dataType = "String", required = false),
            @ApiImplicitParam(name = "testerId", value = "监考人id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "testFile", value = "考试内容文件id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "scheduleSerial", value = "日程安排流水号", dataType = "Long", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/listTest")
    public List<Test> listTest(@RequestBody @Validated TestVO testVO){
        return testService.listTest(testVO);
    }

    @ApiOperation(value = "分页列考试", notes = "分页列考试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classCode", value = "班级号", dataType = "String", required = false),
            @ApiImplicitParam(name = "testerId", value = "监考人id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "testFile", value = "考试内容文件id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "scheduleSerial", value = "日程安排流水号", dataType = "Long", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "pageSize", value = "页面容量", dataType = "Integer", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/pagedListTest")
    public IPage<Test> pagedListTest(@RequestBody @Validated TestVO testVO){
        return testService.pagedListTest(testVO);
    }

}
