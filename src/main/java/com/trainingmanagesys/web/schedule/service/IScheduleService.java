package com.trainingmanagesys.web.schedule.service;

import com.trainingmanagesys.web.schedule.entity.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-12
 */
public interface IScheduleService extends IService<Schedule> {

    String addSchedule(Schedule schedule);
    String updateSchedule(Schedule schedule);
}
