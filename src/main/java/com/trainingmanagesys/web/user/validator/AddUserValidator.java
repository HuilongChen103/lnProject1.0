package com.trainingmanagesys.web.user.validator;

import com.trainingmanagesys.web.user.vo.AddUserVO;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 除id，enable外的其他属性全都不能为空
 */
public class AddUserValidator implements DefaultGroupSequenceProvider<AddUserVO> {
    @Override
    public List<Class<?>> getValidationGroups(AddUserVO user) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(AddUserVO.class);

        if (null != user){
            // 判断addUser时输入信息是否存在为空
            if (null == user.getName() || null == user.getPassword()){
                defaultGroupSequence.add(AddUserVO.allNotNullGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
