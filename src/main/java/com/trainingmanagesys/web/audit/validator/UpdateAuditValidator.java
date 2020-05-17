package com.trainingmanagesys.web.audit.validator;

import com.trainingmanagesys.web.audit.entity.Audit;
import com.trainingmanagesys.web.finance.entity.Finance;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateAuditValidator implements DefaultGroupSequenceProvider<Audit> {
    @Override
    public List<Class<?>> getValidationGroups(Audit audit) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Audit.class);

        if (null != audit){
            if (audit.getAuditSerial() == null){
                defaultGroupSequence.add(Audit.basicNotNullGroup.class);
                return defaultGroupSequence;
            }

            // 判断updateAudit时输入信息是否全部为空
            if (null == audit.getAuditorId() && null == audit.getApplicantId() &&
                null == audit.getEventCode() && null == audit.getEvent() &&
                null == audit.getApplyDate() && null == audit.getAuditDate() &&
                null == audit.getState() && null == audit.getComment()){
                defaultGroupSequence.add(Audit.notAllNullGroup.class);
            }
        }
        return defaultGroupSequence;
    }
}
