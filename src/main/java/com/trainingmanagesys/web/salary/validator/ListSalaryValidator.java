package com.trainingmanagesys.web.salary.validator;

import com.trainingmanagesys.web.salary.entity.Salary;
import com.trainingmanagesys.web.salary.vo.ListSalaryVO;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class ListSalaryValidator implements DefaultGroupSequenceProvider<ListSalaryVO> {
    @Override
    public List<Class<?>> getValidationGroups(ListSalaryVO salary) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(ListSalaryVO.class);

        if (null != salary){
            if (salary.getYear() == null && salary.getMonth() != null){
                defaultGroupSequence.add(ListSalaryVO.yearNotNullGroup.class);
                return defaultGroupSequence;
            }
        }
        return defaultGroupSequence;
    }
}
