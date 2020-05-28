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

import java.util.Date;
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
        temp.setStartTime(schedule.getStartTime());
        temp.setEndTime(schedule.getEndTime());
        temp.setYear(schedule.getYear());
        temp.setSemester(schedule.getSemester());
        temp.setWeek(schedule.getWeek());
        temp.setStartDate(schedule.getStartDate());
        temp.setEndDate(schedule.getEndDate());
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

//        Date passedStartTime = schedule.getStartTime();
//        Date passedEndTime = schedule.getEndTime();
//        Date originStartTime = temp.getStartTime();
//        Date originEndTime = temp.getEndTime();
//
//        // 判断开始时间和结束时间之间的关系
//        // 如果传过来的包含有开始时间，没有结束时间
//        if (passedStartTime != null && passedEndTime == null){
//            // 如果数据库里的这条数据没有endTime
//            if (originEndTime == null)
//                throw new APIException("该数据没有结束时间，请设置结束时间");
//            if (originEndTime.before(passedStartTime))
//                throw new APIException("设置的开始时间错误，比结束时间早");
//        }
//        // 如果传过来的包含有结束时间没有开始时间
//        if (passedStartTime == null && passedEndTime != null){
//            // 如果数据库里面这条数据没有开始时间
//            if (originStartTime == null)
//                throw new APIException("该数据没有开始时间，请设置开始时间");
//            // 如果数据库里面这条数据开始时间比结束时间玩
//            if (originStartTime.after(passedEndTime))
//                throw new APIException("设置的结束时间错误，比开始时间早");
//        }
//
//        if (temp.getStartTime().after(temp.getEndTime()))
//            throw new APIException("时间设置错误，开始时间晚于结束时间");
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
