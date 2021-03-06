package com.trainingmanagesys.web.goods.validator;

import com.trainingmanagesys.web.assessment.entity.Assessment;
import com.trainingmanagesys.web.goods.entity.Goods;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateGoodsValidator implements DefaultGroupSequenceProvider<Goods> {
    @Override
    public List<Class<?>> getValidationGroups(Goods goods) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Goods.class);

        if (null != goods){
            if (goods.getGoodsCode() == null){
                defaultGroupSequence.add(Goods.addKeyGroup.class);
                return defaultGroupSequence;
            }

            if (null == goods.getName() && null == goods.getCatagory() &&
                null == goods.getPicId() && null == goods.getStockInDate() &&
                null == goods.getStockOutDate() && null == goods.getPrice() &&
                null == goods.getRoomNum() && null == goods.getComment()){
                defaultGroupSequence.add(Goods.updateGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
