package com.trainingmanagesys.web.user.validator;

import com.trainingmanagesys.web.user.vo.UpdateUserVO;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateUserValidator implements DefaultGroupSequenceProvider<UpdateUserVO> {
    @Override
    public List<Class<?>> getValidationGroups(UpdateUserVO user) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(UpdateUserVO.class);

        if (null != user){
            if (user.getUid() == null){
                defaultGroupSequence.add(UpdateUserVO.userIdRequiredGroup.class);
                return defaultGroupSequence;
            }

            // 判断updateUser时输入信息是否全部为空
            if (null == user.getName() && null == user.getGender() &&
                null == user.getPosition() && null == user.getBirthday() &&
                null == user.getPassword()){
                defaultGroupSequence.add(UpdateUserVO.notAllNullGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
