package com.trainingmanagesys.web.goods.validator;

import com.trainingmanagesys.web.goods.entity.Goods;
import com.trainingmanagesys.web.goods.entity.Goodsusage;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateGoodsusageValidator implements DefaultGroupSequenceProvider<Goodsusage> {
    @Override
    public List<Class<?>> getValidationGroups(Goodsusage goodsusage) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Goodsusage.class);

        if (null != goodsusage){
            if (goodsusage.getUsageCode() == null){
                defaultGroupSequence.add(Goodsusage.addKeyGroup.class);
                return defaultGroupSequence;
            }

            if (null == goodsusage.getRentorId() && null == goodsusage.getPicId() &&
                null == goodsusage.getRentDate() && null == goodsusage.getReturnDate()){
                defaultGroupSequence.add(Goodsusage.updateGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
