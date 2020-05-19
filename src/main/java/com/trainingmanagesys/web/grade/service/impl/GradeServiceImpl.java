package com.trainingmanagesys.web.grade.service.impl;

import com.trainingmanagesys.web.grade.entity.Grade;
import com.trainingmanagesys.web.grade.dao.GradeMapper;
import com.trainingmanagesys.web.grade.service.IGradeService;
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
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements IGradeService {

    @Override
    public Long addGrade(Grade grade) {
        baseMapper.insert(grade);
        return grade.getGradeSerial();
    }

    @Override
    public String updateGrade(Grade grade) {
        String result = "添加成绩失败";
        int code = baseMapper.insert(grade);
        if (code == 1)
            result = "添加成绩成功";
        return result;
    }

    @Override
    public String deleteGrade(Long gradeSerial) {
        String result = "删除成绩失败";
        int code = baseMapper.deleteById(gradeSerial);
        if (code == 1)
            result = "删除成绩成功";
        return result;
    }

    @Override
    public Grade getGrade(Long gradeSerial) {
        return baseMapper.selectById(gradeSerial);
    }
}
