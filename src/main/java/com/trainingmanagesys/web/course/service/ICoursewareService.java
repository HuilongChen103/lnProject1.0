package com.trainingmanagesys.web.course.service;

import com.trainingmanagesys.web.course.entity.Courseware;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
public interface ICoursewareService extends IService<Courseware> {

    Long addCourseware(Courseware courseware);

    String updateCourseware(Courseware courseware);

    String deleteCourseware(Long coursewareSerial);

    Courseware getCourseware(Long coursewareSerial);
}
