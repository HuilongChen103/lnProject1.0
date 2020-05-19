package com.trainingmanagesys.web.messageboard.validator;

import com.trainingmanagesys.web.messageboard.entity.Messageboard;
import com.trainingmanagesys.web.teacher.entity.Teacourse;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateMessageValidator implements DefaultGroupSequenceProvider<Messageboard> {
    @Override
    public List<Class<?>> getValidationGroups(Messageboard messageboard) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Messageboard.class);

        if (null != messageboard){
            if (messageboard.getMessageSerial() == null || messageboard.getUploaderId() == null){
                defaultGroupSequence.add(Messageboard.basicNotNullGroup.class);
                return defaultGroupSequence;
            }

            if (null == messageboard.getClassCode() && null == messageboard.getContent() &&
                null == messageboard.getDate()){
                defaultGroupSequence.add(Messageboard.notAllNullGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
