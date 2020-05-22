package com.trainingmanagesys.web.benefitevaluation.validator;

import com.trainingmanagesys.web.benefitevaluation.entity.Benefitevaluation;
import com.trainingmanagesys.web.benefitevaluation.vo.AddBenefitevaluationVO;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class AddBenefitevaluationValidator implements DefaultGroupSequenceProvider<AddBenefitevaluationVO> {
    @Override
    public List<Class<?>> getValidationGroups(AddBenefitevaluationVO benefitevaluation) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(AddBenefitevaluationVO.class);

        if (null != benefitevaluation){
            if (benefitevaluation.getBenefitSerial() == null){
                defaultGroupSequence.add(AddBenefitevaluationVO.addKeyGroup.class);
                return defaultGroupSequence;
            }

            // 在声明月份的时候需要声明年份
            if (null == benefitevaluation.getYear() && null != benefitevaluation.getMonth())
                defaultGroupSequence.add(AddBenefitevaluationVO.yearNotNullGroup.class);

        }
        return defaultGroupSequence;
    }
}
