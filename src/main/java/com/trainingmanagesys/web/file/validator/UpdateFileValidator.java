package com.trainingmanagesys.web.file.validator;

import com.trainingmanagesys.web.file.entity.File;
import com.trainingmanagesys.web.finance.entity.Finance;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UpdateFileValidator implements DefaultGroupSequenceProvider<File> {
    @Override
    public List<Class<?>> getValidationGroups(File file) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        // 指定对象
        defaultGroupSequence.add(File.class);

        if (null != file){
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
