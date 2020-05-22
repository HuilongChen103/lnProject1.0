package com.trainingmanagesys.web.assessment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.assessment.entity.Assessment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.assessment.vo.AddAssessmentVO;
import com.trainingmanagesys.web.assessment.vo.AssessmentVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
public interface IAssessmentService extends IService<Assessment> {

    Long addAssessment(AddAssessmentVO vo);

    String updateAssessment(Assessment assessment);

    String deleteAssessment(Long assessSerial);

    Assessment getAssessment(Long assessSerial);

    List<Assessment> listAssessment(AssessmentVO vo);

    IPage<Assessment> pagedListAssessment(AssessmentVO vo);
}
