package com.trainingmanagesys.web.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.student.entity.Stucourse;
import com.trainingmanagesys.web.student.dao.StucourseMapper;
import com.trainingmanagesys.web.student.service.IStucourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.student.vo.StucourseVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-13
 */
@Service
public class StucourseServiceImpl extends ServiceImpl<StucourseMapper, Stucourse> implements IStucourseService {

    private void checkStucourseExistence(Long scSerial){
        if (getStuCourse(scSerial) == null)
            throw new APIException("该学生课程不存在");
    }

    @Override
    public String addStuCourse(Stucourse stucourse) {
        String result = "添加学生课程失败";
        int code = baseMapper.insert(stucourse);
        if (code == 1)
            result = "添加学生课程成功";
        return result;
    }

    @Override
    public String updateStuCourse(Stucourse stucourse) {
        checkStucourseExistence(stucourse.getScSerial());
        String result = "更新学生课程失败";
        int code = baseMapper.updateById(stucourse);
        if (code == 1)
            result = "更新学生课程成功";
        return result;
    }

    @Override
    public String deleteStuCourse(Long scSerial) {
        checkStucourseExistence(scSerial);
        String result = "删除学生课程失败";
        int code = baseMapper.deleteById(scSerial);
        if (code == 1)
            result = "删除学生课程成功";
        return result;
    }

    @Override
    public Stucourse getStuCourse(Long scSerial) {
        return baseMapper.selectById(scSerial);
    }

    @Override
    public List<Stucourse> listStuCourse(StucourseVO stucourseVO) {
        QueryWrapper<Stucourse> queryWrapper = new QueryWrapper<>();
        if (stucourseVO.getStudentId() != null) queryWrapper.eq("student_id", stucourseVO.getStudentId());
        if (stucourseVO.getCourseCode() != null) queryWrapper.eq("course_code", stucourseVO.getCourseCode());
        if (stucourseVO.getClassCode() != null) queryWrapper.eq("class_code", stucourseVO.getClassCode());
        if (stucourseVO.getFeeMax() != null) queryWrapper.le("fee", stucourseVO.getFeeMax());
        if (stucourseVO.getFeeMin() != null) queryWrapper.ge("fee", stucourseVO.getFeeMin());
        if (stucourseVO.getPay() != null) queryWrapper.eq("pay", stucourseVO.getPay());
        if (stucourseVO.getFinanceCode() != null) queryWrapper.eq("finance_code", stucourseVO.getFinanceCode());
        if (stucourseVO.getLimit() != null) queryWrapper.last(" limit " + stucourseVO.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Stucourse> pagedListStuCourse(StucourseVO stucourseVO) {
        QueryWrapper<Stucourse> queryWrapper = new QueryWrapper<>();
        if (stucourseVO.getStudentId() != null) queryWrapper.eq("student_id", stucourseVO.getStudentId());
        if (stucourseVO.getCourseCode() != null) queryWrapper.eq("course_code", stucourseVO.getCourseCode());
        if (stucourseVO.getClassCode() != null) queryWrapper.eq("class_code", stucourseVO.getClassCode());
        if (stucourseVO.getFeeMax() != null) queryWrapper.le("fee", stucourseVO.getFeeMax());
        if (stucourseVO.getFeeMin() != null) queryWrapper.ge("fee", stucourseVO.getFeeMin());
        if (stucourseVO.getPay() != null) queryWrapper.eq("pay", stucourseVO.getPay());
        if (stucourseVO.getFinanceCode() != null) queryWrapper.eq("finance_code", stucourseVO.getFinanceCode());
        if (stucourseVO.getLimit() != null) queryWrapper.last(" limit " + stucourseVO.getLimit());

        Page<Stucourse> page = new Page<>();
        page.setCurrent(stucourseVO.getCurrentPage());
        page.setSize(stucourseVO.getPageSize());
        IPage<Stucourse> pagedList = baseMapper.selectPage(page, queryWrapper);
        return pagedList;
    }
}
