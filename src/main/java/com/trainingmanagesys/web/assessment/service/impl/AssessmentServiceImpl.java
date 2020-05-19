package com.trainingmanagesys.web.assessment.service.impl;

import com.trainingmanagesys.web.assessment.entity.Assessment;
import com.trainingmanagesys.web.assessment.dao.AssessmentMapper;
import com.trainingmanagesys.web.assessment.service.IAssessmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
@Service
public class AssessmentServiceImpl extends ServiceImpl<AssessmentMapper, Assessment> implements IAssessmentService {

    @Override
    public Long addAssessment(Assessment assessment) {
        baseMapper.insert(assessment);
        return assessment.getAssessorId();
    }

    @Override
    public String updateAssessment(Assessment assessment) {
        String result = "更新测评失败";
        int code = baseMapper.updateById(assessment);
        if (code == 1)
            result = "更新测评成功";
        return result;
    }

    @Override
    public String deleteAssessment(Long assessSerial) {
        String result = "删除测评失败";
        int code = baseMapper.deleteById(assessSerial);
        if (code == 1)
            result = "删除测评成功";
        return result;
    }

    @Override
    public Assessment getAssessment(Long assessSerial) {
        return baseMapper.selectById(assessSerial);
    }
}
