package com.trainingmanagesys.web.benefitevaluation.validator;

import com.trainingmanagesys.web.benefitevaluation.entity.Benefitevaluation;
import com.trainingmanagesys.web.file.entity.File;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateBenefitevaluationValidator implements DefaultGroupSequenceProvider<Benefitevaluation> {
    @Override
    public List<Class<?>> getValidationGroups(Benefitevaluation benefitevaluation) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(Benefitevaluation.class);

        if (null != benefitevaluation){
            if (file.getFileSerial() == null){
                defaultGroupSequence.add(File.basicNotNullGroup.class);
                return defaultGroupSequence;
            }

            // 判断updateUser时输入信息是否全部为空
            if (null == file.getName() && null == file.getDisplayname() &&
                null == file.getExtension() && null == file.getContenttype() &&
                null == file.getFileData() && null == file.getFileSize() &&
                null == file.getUploadTime() && null == file.getUploaderId()){
                defaultGroupSequence.add(File.notAllNullGroup.class);
            }

        }
        return defaultGroupSequence;
    }
}
