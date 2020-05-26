package com.trainingmanagesys.web.teacher.validator;

import com.trainingmanagesys.web.student.entity.Stucourse;
import com.trainingmanagesys.web.teacher.entity.Teacourse;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateTeacourseValidator implements DefaultGroupSequenceProvider<Teacourse> {
    @Override
    public List<Class<?>> getValidationGroups(Teacourse teacourse) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Teacourse.class);

        if (null != teacourse){
            if (teacourse.getTcSerial() == null){
                defaultGroupSequence.add(Teacourse.addKeyGroup.class);
                return defaultGroupSequence;
            }
            if (null == teacourse.getTeacherId() && null == teacourse.getCourseCode() &&
                null == teacourse.getClassCode() && null == teacourse.getPercentage() &&
                null == teacourse.getRemain() && null == teacourse.getIntro()){
                defaultGroupSequence.add(Teacourse.updateGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
