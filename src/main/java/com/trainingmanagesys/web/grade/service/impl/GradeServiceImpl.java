package com.trainingmanagesys.web.grade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.grade.entity.Grade;
import com.trainingmanagesys.web.grade.dao.GradeMapper;
import com.trainingmanagesys.web.grade.service.IGradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.grade.vo.GradeVO;
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
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements IGradeService {

    private void checkGradeExistence(Long gradeSerial){
        if (getGrade(gradeSerial) == null)
            throw new APIException("该成绩不存在");
    }

    @Override
    public Long addGrade(Grade grade) {
        baseMapper.insert(grade);
        return grade.getGradeSerial();
    }

    @Override
    public String updateGrade(Grade grade) {
        checkGradeExistence(grade.getGradeSerial());
        String result = "添加成绩失败";
        int code = baseMapper.insert(grade);
        if (code == 1)
            result = "添加成绩成功";
        return result;
    }

    @Override
    public String deleteGrade(Long gradeSerial) {
        checkGradeExistence(gradeSerial);
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

    @Override
    public List<Grade> listGrade(GradeVO vo) {
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        if (vo.getTestSerial() != null) queryWrapper.eq("test_serial", vo.getTestSerial());
        if (vo.getStudentId() != null) queryWrapper.eq("student_id", vo.getStudentId());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Grade> pagedListGrade(GradeVO vo) {
        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        if (vo.getTestSerial() != null) queryWrapper.eq("test_serial", vo.getTestSerial());
        if (vo.getStudentId() != null) queryWrapper.eq("student_id", vo.getStudentId());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());

        Page<Grade> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getPageSize());
        IPage<Grade> pagedList = baseMapper.selectPage(page, queryWrapper);
        return pagedList;
    }
}
