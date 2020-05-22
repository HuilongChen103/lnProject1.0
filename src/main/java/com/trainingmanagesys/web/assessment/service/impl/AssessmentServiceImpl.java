package com.trainingmanagesys.web.assessment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.assessment.entity.Assessment;
import com.trainingmanagesys.web.assessment.dao.AssessmentMapper;
import com.trainingmanagesys.web.assessment.service.IAssessmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.assessment.vo.AddAssessmentVO;
import com.trainingmanagesys.web.assessment.vo.AssessmentVO;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private void checkAssessmentExistence(Long assessSerial){
        if (getAssessment(assessSerial) == null)
            throw new APIException("该测评不存在");
    }

    @Override
    public Long addAssessment(AddAssessmentVO vo) {
        Assessment assessment = new Assessment();
        assessment.setTargetId(vo.getTargetId());
        assessment.setAssessorId(vo.getAssessorId());
        // 学期只能在声明了学年的情况下添加
        if (vo.getYear() != null){
            assessment.setYear(vo.getYear());
            assessment.setSemester(vo.getSemester());
        }
        assessment.setEventCode(vo.getEventCode());
        assessment.setGrade(vo.getGrade());
        assessment.setComment(vo.getComment());
        baseMapper.insert(assessment);
        return assessment.getAssessorId();
    }

    @Override
    public String updateAssessment(Assessment assessment) {
        checkAssessmentExistence(assessment.getAssessSerial());
        // 更新的时候存在学年为空，但是要更新学期数据的情况，这种情况应该判断并作出提醒
        Assessment tempAssessment = getAssessment(assessment.getAssessSerial());
        if (tempAssessment.getYear() == null && assessment.getSemester() != null)
            throw new APIException("请声明学年");
        String result = "更新测评失败";
        int code = baseMapper.updateById(assessment);
        if (code == 1)
            result = "更新测评成功";
        return result;
    }

    @Override
    public String deleteAssessment(Long assessSerial) {
        checkAssessmentExistence(assessSerial);
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

    @Override
    public List<Assessment> listAssessment(AssessmentVO vo) {
        QueryWrapper<Assessment> queryWrapper = new QueryWrapper<>();
        if (vo.getTargetId() != null) queryWrapper.eq("target_id", vo.getTargetId());
        if (vo.getAssessorId() != null) queryWrapper.eq("assessor_id", vo.getAssessorId());
        if (vo.getYear() != null) {
            queryWrapper.eq("year", vo.getYear());
            if (vo.getSemester() != null) queryWrapper.eq("semester", vo.getSemester());
        }
        if (vo.getEventCode() != null) queryWrapper.eq("event_code", vo.getEventCode());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Assessment> pagedListAssessment(AssessmentVO vo) {
        QueryWrapper<Assessment> queryWrapper = new QueryWrapper<>();
        if (vo.getTargetId() != null) queryWrapper.eq("target_id", vo.getTargetId());
        if (vo.getAssessorId() != null) queryWrapper.eq("assessor_id", vo.getAssessorId());
        if (vo.getYear() != null) {
            queryWrapper.eq("year", vo.getYear());
            if (vo.getSemester() != null) queryWrapper.eq("semester", vo.getSemester());
        }
        if (vo.getEventCode() != null) queryWrapper.eq("event_code", vo.getEventCode());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());

        Page<Assessment> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getPageSize());
        IPage<Assessment> pagedList = baseMapper.selectPage(page, queryWrapper);
        return pagedList;
    }
}
