package com.trainingmanagesys.web.grade.service;

import com.trainingmanagesys.web.grade.entity.Grade;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
public interface IGradeService extends IService<Grade> {

    Long addGrade(Grade grade);

    String updateGrade(Grade grade);

    String deleteGrade(Long gradeSerial);

    Grade getGrade(Long gradeSerial);
}
