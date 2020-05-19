package com.trainingmanagesys.web.assessment.service;

import com.trainingmanagesys.web.assessment.entity.Assessment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
public interface IAssessmentService extends IService<Assessment> {

    Long addAssessment(Assessment assessment);

    String updateAssessment(Assessment assessment);

    String deleteAssessment(Long assessSerial);

    Assessment getAssessment(Long assessSerial);
}
