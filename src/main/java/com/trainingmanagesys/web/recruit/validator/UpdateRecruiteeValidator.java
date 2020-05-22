package com.trainingmanagesys.web.recruit.validator;

import com.trainingmanagesys.web.recruit.entity.Recruit;
import com.trainingmanagesys.web.recruit.entity.Recruitee;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateRecruiteeValidator implements DefaultGroupSequenceProvider<Recruitee> {
    @Override
    public List<Class<?>> getValidationGroups(Recruitee recruitee) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Recruitee.class);

        if (null != recruitee){
            if (recruitee.getRecruiteeCode() == null){
                defaultGroupSequence.add(Recruitee.addKeyGroup.class);
                return defaultGroupSequence;
            }

            if (null == recruitee.getRecruitCode() && null == recruitee.getName() &&
                null == recruitee.getResumeFile() && null == recruitee.getCatagory() &&
                null == recruitee.getAuditSerial()){
                defaultGroupSequence.add(Recruitee.updateGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
