package com.trainingmanagesys.web.schedule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.schedule.entity.Schedule;
import com.trainingmanagesys.web.schedule.dao.ScheduleMapper;
import com.trainingmanagesys.web.schedule.service.IScheduleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.schedule.vo.AddScheduleVO;
import com.trainingmanagesys.web.schedule.vo.ListScheduleVO;
import com.trainingmanagesys.web.schedule.vo.PagedListScheduleVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-12
 */
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements IScheduleService {

    private Schedule checkScheduleExistence(Long scheduleSerial){
        Schedule schedule = getSchedule(scheduleSerial);
        if (schedule == null)
            throw new APIException("该日程安排不存在");
        return schedule;
    }

    @Override
    public String addSchedule(AddScheduleVO schedule) {
        Schedule temp = new Schedule();
        temp.setEventCode(schedule.getEventCode());
        temp.setTime(schedule.getTime());
        temp.setYear(schedule.getYear());
        temp.setSemester(schedule.getSemester());
        temp.setWeek(schedule.getWeek());
        String result = "添加日程安排失败";
        int code = baseMapper.insert(temp);
        if (code == 1)
            result = "添加日程安排成功";
        return result;
    }

    @Override
    public String updateSchedule(Schedule schedule) {
        Schedule temp = checkScheduleExistence(schedule.getScheduleSerial());

        // 如果年份不存在，更新学期或者周则报错
        if (temp.getYear() == null && (schedule.getSemester() != null || schedule.getWeek() != null))
            throw new APIException("请指明年份");

        // 如果年份存在，但是学期不存在，跟新周报错
        if (temp.getYear() != null && temp.getSemester() == null && schedule.getWeek() != null)
            throw new APIException("请指明学期");
        String result = "更新日程安排失败";
        int code = baseMapper.updateById(schedule);
        if (code == 1)
            result = "更新日程安排成功";
        return result;
    }

    @Override
    public Schedule getSchedule(Long scheduleSerial) {
        return baseMapper.selectById(scheduleSerial);
    }

    @Override
    public String deleteSchedule(Long scheduleSerial) {
        checkScheduleExistence(scheduleSerial);
        String result = "删除日程安排失败";
        int code = baseMapper.deleteById(scheduleSerial);
        if (code == 1)
            result = "删除日程安排成功";
        return result;
    }

    @Override
    public List<Schedule> listSchedule(ListScheduleVO vo) {
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        if (vo.getEventCode() != null) queryWrapper.eq("event_code", vo.getEventCode());
        if (vo.getTime() != null) queryWrapper.eq("time", vo.getTime());
        // 判断输入年、输入年季度、输入年季度周
        if (vo.getYear() != null){
            queryWrapper.eq("year", vo.getYear());
            if (vo.getSemester() != null){
                queryWrapper.eq("semester", vo.getSemester());
                if (vo.getWeek() != null){
                    queryWrapper.eq("week", vo.getWeek());
                }
            }
        }
        // 这里应该有对错误情况（如年不存在，月学期存在）的报错提醒
        // 但是数据校验那里写了，这里不写应该也可以
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Schedule> pagedListSchedule(PagedListScheduleVO vo) {
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        if (vo.getEventCode() != null) queryWrapper.eq("event_code", vo.getEventCode());
        if (vo.getTime() != null) queryWrapper.eq("time", vo.getTime());
        // 判断输入年、输入年季度、输入年季度周
        if (vo.getYear() != null){
            queryWrapper.eq("year", vo.getYear());
            if (vo.getSemester() != null){
                queryWrapper.eq("semester", vo.getSemester());
                if (vo.getWeek() != null){
                    queryWrapper.eq("week", vo.getWeek());
                }
            }
        }

        Page<Schedule> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getPageSize());
        return baseMapper.selectPage(page, queryWrapper);
    }
}
