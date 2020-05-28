package com.trainingmanagesys.web.schedule.validator;

import com.trainingmanagesys.web.schedule.entity.Schedule;
import com.trainingmanagesys.web.user.entity.User;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateScheduleValidator implements DefaultGroupSequenceProvider<Schedule> {
    @Override
    public List<Class<?>> getValidationGroups(Schedule schedule) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Schedule.class);

        if (null != schedule){
            if (schedule.getScheduleSerial() == null){
                defaultGroupSequence.add(Schedule.addKeyGroup.class);
                return defaultGroupSequence;
            }

            // 判断updateSchedule时输入信息是否全部为空
            if (null == schedule.getEventCode() && null == schedule.getStartTime() &&
                null == schedule.getWeek() && null == schedule.getYear() &&
                null == schedule.getSemester() && null == schedule.getEndTime() &&
                null == schedule.getStartDate() && null == schedule.getEndDate()){
                defaultGroupSequence.add(Schedule.updateGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
