package com.trainingmanagesys.web.homework.service;

import com.trainingmanagesys.web.homework.entity.Homework;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-13
 */
public interface IHomeworkService extends IService<Homework> {

    Long addHomework(Homework homework);

    String updateHomework(Homework homework);

    String deleteHomework(Long hwSerial);

    Homework getHomework(Long hwSerial);
}
