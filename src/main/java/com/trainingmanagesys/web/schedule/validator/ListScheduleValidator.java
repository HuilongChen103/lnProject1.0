package com.trainingmanagesys.web.schedule.validator;

import com.trainingmanagesys.web.schedule.entity.Schedule;
import com.trainingmanagesys.web.schedule.vo.ListScheduleVO;
import com.trainingmanagesys.web.user.entity.User;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class ListScheduleValidator implements DefaultGroupSequenceProvider<ListScheduleVO> {
    @Override
    public List<Class<?>> getValidationGroups(ListScheduleVO schedule) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(ListScheduleVO.class);

        if (null != schedule){

            // 判断是否存在类似在没有确定年的情况下，输入学期或者周
            if (null == schedule.getSemester() || null == schedule.getWeek()){
                defaultGroupSequence.add(ListScheduleVO.yearNotNullGroup.class);
            }

            if (null != schedule.getYear() && null != schedule.getWeek()){
                defaultGroupSequence.add(ListScheduleVO.semesterNotNullGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
