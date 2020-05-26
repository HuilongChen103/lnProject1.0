package com.trainingmanagesys.web.schedule.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.utils.DateDiff;
import com.trainingmanagesys.web.schedule.entity.Schedule;
import com.trainingmanagesys.web.schedule.service.IScheduleService;
import com.trainingmanagesys.web.schedule.vo.AddScheduleVO;
import com.trainingmanagesys.web.schedule.vo.ListScheduleVO;
import com.trainingmanagesys.web.schedule.vo.PagedListScheduleVO;
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
import java.text.ParseException;
import java.util.Date;
import java.util.List;

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
@Validated
public class ScheduleController {

    @Autowired
    IScheduleService scheduleService;

    @ApiOperation(value = "添加日程安排", notes = "如果输入周数的话，则需指明年和季度；季度同理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eventCode", value = "事件编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "time", value = "时间段", dataType = "Date", required = false),
            @ApiImplicitParam(name = "week", value = "周数", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "year", value = "年份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "semester", value = "季度", dataType = "String", required = false)
    })
    @PostMapping("/addSchedule")
    @Transactional(rollbackFor = Exception.class)
    public String addSchedule(@RequestBody @Validated AddScheduleVO schedule){
        return scheduleService.addSchedule(schedule);
    }

    @ApiOperation(value = "更新日程安排")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scheduleSerial", value = "日程id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "eventCode", value = "事件编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "time", value = "时间段", dataType = "Date", required = false),
            @ApiImplicitParam(name = "week", value = "周数", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "year", value = "年份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "semester", value = "季度", dataType = "String", required = false)
    })
    @PostMapping("/updateSchedule")
    @Transactional(rollbackFor = Exception.class)
    public String updateSchedule(@RequestBody @Validated Schedule schedule){
        return scheduleService.updateSchedule(schedule);
    }

    @ApiOperation(value = "删除日程安排")
    @ApiImplicitParam(name = "scheduleSerial", value = "日程id", dataType = "Long", required = true)
    @PostMapping("/deleteSchedule")
    @Transactional(rollbackFor = Exception.class)
    public String deleteSchedule(@NotNull(message = "请指明日程id") Long scheduleSerial){
        return scheduleService.deleteSchedule(scheduleSerial);
    }

    @ApiOperation(value = "获得日程安排")
    @ApiImplicitParam(name = "scheduleSerial", value = "日程id", dataType = "Long", required = true)
    @PostMapping("/getSchedule")
    public Schedule getSchedule(@NotNull(message = "请指明日程id") Long scheduleSerial){
        return scheduleService.getSchedule(scheduleSerial);
    }

    @ApiOperation(value = "列日程安排")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eventCode", value = "事件编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "time", value = "时间段", dataType = "Date", required = false),
            @ApiImplicitParam(name = "week", value = "周数", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "year", value = "年份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "semester", value = "季度", dataType = "String", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false)
    })
    @PostMapping("/listSchedule")
    public List<Schedule> listSchedule(@RequestBody @Validated ListScheduleVO schedule){
        return scheduleService.listSchedule(schedule);
    }

    @ApiOperation(value = "分页列日程安排")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eventCode", value = "事件编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "time", value = "时间段", dataType = "Date", required = false),
            @ApiImplicitParam(name = "week", value = "周数", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "year", value = "年份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "semester", value = "季度", dataType = "String", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面容量", dataType = "Integer", required = true)
    })
    @PostMapping("/pagedListSchedule")
    public IPage<Schedule> pagedListSchedule(@RequestBody @Validated PagedListScheduleVO schedule){
        return scheduleService.pagedListSchedule(schedule);
    }

    @ApiOperation(value = "比较日期")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date1", value = "日期1", dataType = "Date", required = true),
            @ApiImplicitParam(name = "date2", value = "日期2", dataType = "Date", required = true)
    })
    @PostMapping("/compareDate")
    public Integer compareDate(@NotNull(message = "请输入日期1") Date date1, @NotNull(message = "请输入日期2") Date date2) throws ParseException {
        return DateDiff.getDayDiffer(date1, date2);
    }
}
