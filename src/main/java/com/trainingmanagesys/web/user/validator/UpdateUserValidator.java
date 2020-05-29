package com.trainingmanagesys.web.user.validator;

import com.trainingmanagesys.web.user.entity.User;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateUserValidator implements DefaultGroupSequenceProvider<User> {
    @Override
    public List<Class<?>> getValidationGroups(User user) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(User.class);

        if (null != user){
            if (user.getUid() == null){
                defaultGroupSequence.add(User.addKeyGroup.class);
                return defaultGroupSequence;
            }

            // 判断updateUser时输入信息是否全部为空
            if (null == user.getName() && null == user.getGender() &&
                null == user.getPosition() && null == user.getBirthday() &&
                null == user.getPassword() && null == user.getTel()){
                defaultGroupSequence.add(User.updateGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
