package com.trainingmanagesys.web.stuff.validator;

import com.trainingmanagesys.web.student.entity.Stucourse;
import com.trainingmanagesys.web.stuff.entity.Stuff;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateStuffValidator implements DefaultGroupSequenceProvider<Stuff> {
    @Override
    public List<Class<?>> getValidationGroups(Stuff stuff) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Stuff.class);

        if (null != stuff){
            if (stuff.getStuffId() == null){
                defaultGroupSequence.add(Stuff.addKeyGroup.class);
                return defaultGroupSequence;
            }

            // 判断updateUser时输入信息是否全部为空
            if (null == stuff.getDepartmentId() && null == stuff.getPosition()){
                defaultGroupSequence.add(Stuff.updateGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
