package com.trainingmanagesys.web.finance.validator;

import com.trainingmanagesys.web.finance.entity.Finance;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateFinanceValidator implements DefaultGroupSequenceProvider<Finance> {
    @Override
    public List<Class<?>> getValidationGroups(Finance finance) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Finance.class);

        if (null != finance){
            if (finance.getFinanceCode() == null){
                defaultGroupSequence.add(Finance.addKeyGroup.class);
                return defaultGroupSequence;
            }

            // 判断updateUser时输入信息是否全部为空
            if (null == finance.getInOut() && null == finance.getPicId() &&
                null == finance.getPayAccount() && null == finance.getReceiveAccount() &&
                null == finance.getTradeMethod() && null == finance.getAmount() &&
                null == finance.getDate() && null == finance.getComment() &&
                null == finance.getCategory()){
                defaultGroupSequence.add(Finance.addAdditionGroup.class);
            }

        }
        return defaultGroupSequence;
    }
}
