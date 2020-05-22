package com.trainingmanagesys.web.benefitevaluation.validator;

import com.trainingmanagesys.web.benefitevaluation.entity.Benefitevaluation;
import com.trainingmanagesys.web.file.entity.File;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateBenefitevaluationValidator implements DefaultGroupSequenceProvider<Benefitevaluation> {
    @Override
    public List<Class<?>> getValidationGroups(Benefitevaluation benefitevaluation) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Benefitevaluation.class);

        if (null != benefitevaluation){
            if (benefitevaluation.getBenefitSerial() == null){
                defaultGroupSequence.add(Benefitevaluation.addKeyGroup.class);
                return defaultGroupSequence;
            }

            // 判断updateUser时输入信息是否全部为空
            if (null == benefitevaluation.getStuffId() && null == benefitevaluation.getYear() &&
                null == benefitevaluation.getMonth() && null == benefitevaluation.getBenefit() &&
                null == benefitevaluation.getAssessment()){
                defaultGroupSequence.add(Benefitevaluation.updateGroup.class);
            }

            if (null == benefitevaluation.getYear() && null != benefitevaluation.getMonth())
                defaultGroupSequence.add(Benefitevaluation.yearNotNullGroup.class);

        }
        return defaultGroupSequence;
    }
}
