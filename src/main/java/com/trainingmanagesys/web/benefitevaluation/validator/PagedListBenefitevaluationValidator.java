package com.trainingmanagesys.web.benefitevaluation.validator;

import com.trainingmanagesys.web.benefitevaluation.entity.Benefitevaluation;
import com.trainingmanagesys.web.benefitevaluation.vo.BenefitevaluationVO;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class PagedListBenefitevaluationValidator implements DefaultGroupSequenceProvider<BenefitevaluationVO> {
    @Override
    public List<Class<?>> getValidationGroups(BenefitevaluationVO benefitevaluationVO) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(BenefitevaluationVO.class);

        if (null != benefitevaluationVO){
            if (benefitevaluationVO.getCurrentPage() == null ||
                benefitevaluationVO.getPageSize() == null){
                defaultGroupSequence.add(BenefitevaluationVO.basicNotNullGroup.class);
                return defaultGroupSequence;
            }

            // 有月份的时候必须有年
            if (null == benefitevaluationVO.getYear() && null != benefitevaluationVO.getMonth())
                defaultGroupSequence.add(BenefitevaluationVO.yearNotNullGroup.class);
        }
        return defaultGroupSequence;
    }
}
