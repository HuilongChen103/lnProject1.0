package com.trainingmanagesys.web.homework.validator;

import com.trainingmanagesys.web.homework.entity.Homework;
import com.trainingmanagesys.web.student.entity.Stucourse;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateHomeworkValidator implements DefaultGroupSequenceProvider<Homework> {
    @Override
    public List<Class<?>> getValidationGroups(Homework homework) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Homework.class);

        if (null != homework){
            if (homework.getHwSerial() == null){
                defaultGroupSequence.add(Homework.basicNotNullGroup.class);
                return defaultGroupSequence;
            }

            if (null == homework.getArrangeSerial() && null == homework.getGrade() &&
                null == homework.getHwFile() && null == homework.getStudentId()){
                defaultGroupSequence.add(Homework.notAllNullGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
