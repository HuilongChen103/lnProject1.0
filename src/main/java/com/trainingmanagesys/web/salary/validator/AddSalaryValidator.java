package com.trainingmanagesys.web.salary.validator;

import com.trainingmanagesys.web.salary.entity.Salary;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class AddSalaryValidator implements DefaultGroupSequenceProvider<Salary> {
    @Override
    public List<Class<?>> getValidationGroups(Salary salary) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Salary.class);

        if (null != salary){
            if (salary.getYear() == null && salary.getMonth() != null){
                defaultGroupSequence.add(Salary.yearNotNullGroup.class);
                return defaultGroupSequence;
            }
        }
        return defaultGroupSequence;
    }
}
