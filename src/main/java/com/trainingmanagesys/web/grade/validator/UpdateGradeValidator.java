package com.trainingmanagesys.web.grade.validator;

import com.trainingmanagesys.web.goods.entity.Goods;
import com.trainingmanagesys.web.grade.entity.Grade;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateGradeValidator implements DefaultGroupSequenceProvider<Grade> {
    @Override
    public List<Class<?>> getValidationGroups(Grade grade) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Goods.class);

        if (null != grade){
            if (grade.getGradeSerial() == null){
                defaultGroupSequence.add(Grade.addKeyGroup.class);
                return defaultGroupSequence;
            }

            if (null == grade.getTestSerial() && null == grade.getStudentId() &&
                null == grade.getGrade() && null == grade.getComment() ){
                defaultGroupSequence.add(Grade.updateGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
