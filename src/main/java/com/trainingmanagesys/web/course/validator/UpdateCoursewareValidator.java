package com.trainingmanagesys.web.course.validator;

import com.trainingmanagesys.web.course.entity.Course;
import com.trainingmanagesys.web.course.entity.Courseware;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateCoursewareValidator implements DefaultGroupSequenceProvider<Courseware> {
    @Override
    public List<Class<?>> getValidationGroups(Courseware courseware) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Courseware.class);

        if (null != courseware){
            if (courseware.getCoursewareSerial() == null ||
                courseware.getClassCode() == null){
                defaultGroupSequence.add(Courseware.basicNotNullGroup.class);
                return defaultGroupSequence;
            }

            if (null == courseware.getFileSerial() && null == courseware.getTitle()){
                defaultGroupSequence.add(Courseware.notAllNullGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
