package com.trainingmanagesys.web.assessment.validator;

import com.trainingmanagesys.web.assessment.entity.Assessment;
import com.trainingmanagesys.web.assessment.vo.AssessmentVO;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class ListAssessValidator implements DefaultGroupSequenceProvider<AssessmentVO> {
    @Override
    public List<Class<?>> getValidationGroups(AssessmentVO assessment) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(AssessmentVO.class);

        if (null != assessment){
            if (assessment.getCurrentPage() == null ||
                assessment.getPageSize() == null){
                defaultGroupSequence.add(AssessmentVO.listKeyGroup.class);
                return defaultGroupSequence;
            }

            // 判断输入学期的时候需要指明学年
            if (null != assessment.getSemester() && null == assessment.getYear())
                defaultGroupSequence.add(Assessment.yearNotNullGroup.class);
        }
        return defaultGroupSequence;
    }
}
