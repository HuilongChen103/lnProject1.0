package com.trainingmanagesys.web.schedule.controller;


import com.trainingmanagesys.web.schedule.entity.Schedule;
import com.trainingmanagesys.web.schedule.service.IScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2020-05-12
 */
@Api(value = "日程安排", tags = {"日程安排操作接口"})
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    IScheduleService scheduleService;

    @ApiOperation(value = "添加日程安排")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eventCode", value = "事件编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "time", value = "时间段", dataType = "Date", required = false),
            @ApiImplicitParam(name = "week", value = "周数", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "year", value = "年份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "semester", value = "季度", dataType = "String", required = false)
    })
    @PostMapping("/addSchedule")
    public String addSchedule(@RequestBody @Validated Schedule schedule){
        return scheduleService.addSchedule(schedule);
    }

    @ApiOperation(value = "更新日程安排")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scheduleId", value = "日程id", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "eventCode", value = "事件编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "time", value = "时间段", dataType = "Date", required = false),
            @ApiImplicitParam(name = "week", value = "周数", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "year", value = "年份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "semester", value = "季度", dataType = "String", required = false)
    })
    @PostMapping("/updateSchedule")
    public String updateSchedule(@RequestBody @Validated Schedule schedule){
        return scheduleService.updateSchedule(schedule);
    }
}
