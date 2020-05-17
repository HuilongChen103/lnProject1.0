package com.trainingmanagesys.web.file.service;

import com.trainingmanagesys.web.file.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-13
 */
public interface IFileService extends IService<File> {

    String addFile(File file);

    String updateFile(File file);

    File getFile(Long fileSerial);
}
