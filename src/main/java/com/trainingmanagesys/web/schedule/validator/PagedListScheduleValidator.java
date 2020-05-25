package com.trainingmanagesys.web.schedule.validator;

import com.trainingmanagesys.web.schedule.entity.Schedule;
import com.trainingmanagesys.web.schedule.vo.PagedListScheduleVO;
import com.trainingmanagesys.web.user.entity.User;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class PagedListScheduleValidator implements DefaultGroupSequenceProvider<PagedListScheduleVO> {
    @Override
    public List<Class<?>> getValidationGroups(PagedListScheduleVO schedule) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(PagedListScheduleVO.class);

        if (null != schedule){
            if (schedule.getCurrentPage() == null || schedule.getPageSize() == null){
                defaultGroupSequence.add(PagedListScheduleVO.listKeyGroup.class);
                return defaultGroupSequence;
            }

            // 判断是否存在类似在没有确定年的情况下，输入学期或者周
            if (null == schedule.getSemester() || null == schedule.getWeek()){
                defaultGroupSequence.add(PagedListScheduleVO.yearNotNullGroup.class);
            }

            if (null != schedule.getYear() && null != schedule.getWeek()){
                defaultGroupSequence.add(PagedListScheduleVO.semesterNotNullGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
