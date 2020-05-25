package com.trainingmanagesys.web.course.validator;

import com.trainingmanagesys.web.benefitevaluation.entity.Benefitevaluation;
import com.trainingmanagesys.web.course.entity.Course;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateCourseValidator implements DefaultGroupSequenceProvider<Course> {
    @Override
    public List<Class<?>> getValidationGroups(Course course) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Course.class);

        if (null != course){
            if (course.getCourseCode() == null){
                defaultGroupSequence.add(Course.addKeyGroup.class);
                return defaultGroupSequence;
            }

            // 判断updateUser时输入信息是否全部为空
            if (null == course.getCourseName() && null == course.getComment() &&
                null == course.getDuration() &&
                null == course.getType() && null == course.getFee()){
                defaultGroupSequence.add(Course.addAdditionGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
