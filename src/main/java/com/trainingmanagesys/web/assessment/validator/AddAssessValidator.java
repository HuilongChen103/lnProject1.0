package com.trainingmanagesys.web.assessment.validator;

import com.trainingmanagesys.web.assessment.entity.Assessment;
import com.trainingmanagesys.web.assessment.vo.AddAssessmentVO;
import com.trainingmanagesys.web.assessment.vo.AssessmentVO;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class AddAssessValidator implements DefaultGroupSequenceProvider<AddAssessmentVO> {
    @Override
    public List<Class<?>> getValidationGroups(AddAssessmentVO assessment) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(AddAssessmentVO.class);

        if (null != assessment){
            if (assessment.getAssessSerial() == null){
                defaultGroupSequence.add(AddAssessmentVO.addKeyGroup.class);
                return defaultGroupSequence;
            }

            if (assessment.getTargetId() == null){
                defaultGroupSequence.add(AddAssessmentVO.addAdditionGroup.class);
                return defaultGroupSequence;
            }

            // 判断输入学期的时候需要指明学年
            if (null != assessment.getSemester() && null == assessment.getYear())
                defaultGroupSequence.add(AddAssessmentVO.yearNotNullGroup.class);
        }
        return defaultGroupSequence;
    }
}
