package com.trainingmanagesys.web.test.validator;

import com.trainingmanagesys.web.teacher.entity.Teacourse;
import com.trainingmanagesys.web.test.entity.Test;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateTestValidator implements DefaultGroupSequenceProvider<Test> {
    @Override
    public List<Class<?>> getValidationGroups(Test test) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Test.class);

        if (null != test){
            if (test.getTestSerial() == null){
                defaultGroupSequence.add(Test.addKeyGroup.class);
                return defaultGroupSequence;
            }
            if (null == test.getClassCode() && null == test.getTesterId1() &&
                null == test.getTesterId2() && null == test.getTestFile() &&
                null == test.getScheduleSerial()){
                defaultGroupSequence.add(Test.updateGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
