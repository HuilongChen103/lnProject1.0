package com.trainingmanagesys.web.clazz.validator;

import com.trainingmanagesys.web.clazz.entity.Clazz;
import com.trainingmanagesys.web.course.entity.Course;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

// 逻辑校验类，通过继承DefaultGroupSequenceProvider并声明对象为Clazz来对Clazz类进行注解管理
public class UpdateClazzValidator implements DefaultGroupSequenceProvider<Clazz> {
    @Override
    public List<Class<?>> getValidationGroups(Clazz clazz) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Clazz.class);

        if (null != clazz){
            if (clazz.getClassCode() == null){
                // 添加校验group
                defaultGroupSequence.add(Clazz.addKeyGroup.class);
                return defaultGroupSequence;
            }

            // 判断updateUser时输入信息是否全部为空
            if (null == clazz.getCourseCode() && null == clazz.getStudentNum() &&
                null == clazz.getRealNum() && null == clazz.getTeacherId() &&
                null == clazz.getScheduleSerial() && null == clazz.getStudentMax()){
                defaultGroupSequence.add(Clazz.updateGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
