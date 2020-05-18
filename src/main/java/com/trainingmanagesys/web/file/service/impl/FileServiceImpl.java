package com.trainingmanagesys.web.file.service.impl;

import com.trainingmanagesys.web.file.entity.File;
import com.trainingmanagesys.web.file.dao.FileMapper;
import com.trainingmanagesys.web.file.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-13
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

    @Override
    public String addFile(File file) {
        String result = "添加文件失败";
        int code = baseMapper.insert(file);
        if (code == 1)
            result = "添加文件成功";
        return result;
    }

    @Override
    public String updateFile(File file) {
        String result = "更新文件失败";
        int code = baseMapper.updateById(file);
        if (code == 1)
            result = "更新文件成功";
        return result;
    }

    @Override
    public File getFile(Long fileSerial) {
        return baseMapper.selectById(fileSerial);
    }
}
