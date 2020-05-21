package com.trainingmanagesys.web.salary.validator;

import com.trainingmanagesys.web.salary.entity.Salary;
import com.trainingmanagesys.web.student.entity.Stucourse;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateSalaryValidator implements DefaultGroupSequenceProvider<Salary> {
    @Override
    public List<Class<?>> getValidationGroups(Salary salary) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Salary.class);

        if (null != salary){
            if (salary.getSalarySerial() == null){
                defaultGroupSequence.add(Salary.basicNotNullGroup.class);
                return defaultGroupSequence;
            }

            // 判断updateUser时输入信息是否全部为空
            if (null == salary.getStuffId() && null == salary.getBasicSalary() &&
                null == salary.getBonus() && null == salary.getTotalSalary() &&
                null == salary.getInsurance() && null == salary.getMonth() &&
                null == salary.getYear()){
                defaultGroupSequence.add(Salary.notAllNullGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
