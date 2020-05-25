package com.trainingmanagesys.web.schedule.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.schedule.entity.Schedule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.schedule.vo.AddScheduleVO;
import com.trainingmanagesys.web.schedule.vo.ListScheduleVO;
import com.trainingmanagesys.web.schedule.vo.PagedListScheduleVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-12
 */
public interface IScheduleService extends IService<Schedule> {

    String addSchedule(AddScheduleVO schedule);

    String updateSchedule(Schedule schedule);

    Schedule getSchedule(Long scheduleSerial);

    String deleteSchedule(Long scheduleSerial);

    List<Schedule> listSchedule(ListScheduleVO vo);

    IPage<Schedule> pagedListSchedule(PagedListScheduleVO vo);
}
