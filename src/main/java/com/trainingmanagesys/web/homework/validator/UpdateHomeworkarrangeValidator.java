package com.trainingmanagesys.web.homework.validator;

import com.trainingmanagesys.web.homework.entity.Homework;
import com.trainingmanagesys.web.homework.entity.Homeworkarrange;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateHomeworkarrangeValidator implements DefaultGroupSequenceProvider<Homeworkarrange> {
    @Override
    public List<Class<?>> getValidationGroups(Homeworkarrange homeworkarrange) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Homeworkarrange.class);

        if (null != homeworkarrange){
            if (homeworkarrange.getArrangeSerial() == null){
                defaultGroupSequence.add(Homeworkarrange.basicNotNullGroup.class);
                return defaultGroupSequence;
            }

            if (null == homeworkarrange.getClassCode() && null == homeworkarrange.getArrangeFile() &&
                null == homeworkarrange.getContent() && null == homeworkarrange.getDeadline()){
                defaultGroupSequence.add(Homeworkarrange.notAllNullGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
