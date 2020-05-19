package com.trainingmanagesys.web.clazz.service;

import com.trainingmanagesys.web.clazz.entity.Clazz;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
public interface IClazzService extends IService<Clazz> {

    String addClazz(Clazz clazz);

    String updateClazz(Clazz clazz);

    String deleteClazz(String classCode);

    Clazz getClazz(String classCode);
}
