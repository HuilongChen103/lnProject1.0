package com.trainingmanagesys.web.recruit.validator;

import com.trainingmanagesys.web.goods.entity.Goodsusage;
import com.trainingmanagesys.web.recruit.entity.Recruit;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateRecruitValidator implements DefaultGroupSequenceProvider<Recruit> {
    @Override
    public List<Class<?>> getValidationGroups(Recruit recruit) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Recruit.class);

        if (null != recruit){
            if (recruit.getRecruitCode() == null){
                defaultGroupSequence.add(Recruit.addKeyGroup.class);
                return defaultGroupSequence;
            }

            if (null == recruit.getPicId() && null == recruit.getPlace() &&
                null == recruit.getCatagory() && null == recruit.getComment() &&
                null == recruit.getDate() && null == recruit.getMethod()){
                defaultGroupSequence.add(Recruit.updateGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
