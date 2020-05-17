package com.trainingmanagesys.web.schedule.service.impl;

import com.trainingmanagesys.web.schedule.entity.Schedule;
import com.trainingmanagesys.web.schedule.dao.ScheduleMapper;
import com.trainingmanagesys.web.schedule.service.IScheduleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public String addSchedule(Schedule schedule) {
        String result = "添加日程安排失败";
        int code = baseMapper.insert(schedule);
        if (code == 1)
            result = "添加日程安排成功";
        return result;
    }

    @Override
    public String updateSchedule(Schedule schedule) {
        String result = "更新日程安排失败";
        int code = baseMapper.updateById(schedule);
        if (code == 1)
            result = "更新日程安排成功";
        return result;
    }
}
