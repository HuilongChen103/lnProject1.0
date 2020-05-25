package com.trainingmanagesys.web.student.validator;

import com.trainingmanagesys.web.file.entity.File;
import com.trainingmanagesys.web.student.entity.Stucourse;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateStucourseValidator implements DefaultGroupSequenceProvider<Stucourse> {
    @Override
    public List<Class<?>> getValidationGroups(Stucourse stucourse) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Stucourse.class);

        if (null != stucourse){
            if (stucourse.getScSerial() == null){
                defaultGroupSequence.add(Stucourse.addKeyGroup.class);
                return defaultGroupSequence;
            }

            // 判断updateUser时输入信息是否全部为空
            if (null == stucourse.getStudentId() && null == stucourse.getCourseCode() &&
                null == stucourse.getClassCode() && null == stucourse.getFee() &&
                null == stucourse.getFinanceCode() && null == stucourse.getPay()){
                defaultGroupSequence.add(Stucourse.updateGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
