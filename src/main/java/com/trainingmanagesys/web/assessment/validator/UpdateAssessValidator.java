package com.trainingmanagesys.web.assessment.validator;

import com.trainingmanagesys.web.assessment.entity.Assessment;
import com.trainingmanagesys.web.course.entity.Course;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateAssessValidator implements DefaultGroupSequenceProvider<Assessment> {
    @Override
    public List<Class<?>> getValidationGroups(Assessment assessment) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Assessment.class);

        if (null != assessment){
            if (assessment.getAssessSerial() == null ||
                assessment.getTargetId() == null){
                defaultGroupSequence.add(Assessment.basicNotNullGroup.class);
                return defaultGroupSequence;
            }

            // 判断updateUser时输入信息是否全部为空
            if (null == assessment.getAssessorId() && null == assessment.getSemester() &&
                null == assessment.getYear() && null == assessment.getEventCode() &&
                null == assessment.getGrade() && null == assessment.getComment()){
                defaultGroupSequence.add(Assessment.notAllNullGroup.class);
            }

            if (null != assessment.getSemester() && null == assessment.getYear())
                defaultGroupSequence.add(Assessment.semesterWithYearGroup.class);
        }
        return defaultGroupSequence;
    }
}
