package com.trainingmanagesys.web.schedule.validator;

import com.trainingmanagesys.web.salary.vo.AddSalaryVO;
import com.trainingmanagesys.web.schedule.vo.AddScheduleVO;
import com.trainingmanagesys.web.schedule.vo.ListScheduleVO;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class AddScheduleValidator implements DefaultGroupSequenceProvider<AddScheduleVO> {
    @Override
    public List<Class<?>> getValidationGroups(AddScheduleVO schedule) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(AddScheduleVO.class);

        if (null != schedule){

            // 判断是否存在类似在没有确定年的情况下，输入学期或者周
            if (null == schedule.getSemester() || null == schedule.getWeek()){
                defaultGroupSequence.add(AddScheduleVO.yearNotNullGroup.class);
            }

            if (null != schedule.getYear() && null != schedule.getWeek()){
                defaultGroupSequence.add(AddScheduleVO.semesterNotNullGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
