package com.trainingmanagesys.web.salary.validator;

import com.trainingmanagesys.web.salary.vo.ListSalaryVO;
import com.trainingmanagesys.web.salary.vo.SalaryVO;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class PagedListSalaryValidator implements DefaultGroupSequenceProvider<SalaryVO> {
    @Override
    public List<Class<?>> getValidationGroups(SalaryVO salary) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(SalaryVO.class);

        if (null != salary){
            if (salary.getCurrentPage() == null || salary.getPageSize() == null){
                defaultGroupSequence.add(SalaryVO.listKeyGroup.class);
                return defaultGroupSequence;
            }

            // 判断输入月份的时候年份不能为空
            if (salary.getYear() == null && salary.getMonth() != null){
                defaultGroupSequence.add(SalaryVO.yearNotNullGroup.class);
                return defaultGroupSequence;
            }
        }
        return defaultGroupSequence;
    }
}
