package com.trainingmanagesys.web.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.utils.BaseConst;
import com.trainingmanagesys.web.clazz.entity.Clazz;
import com.trainingmanagesys.web.course.entity.Course;
import com.trainingmanagesys.web.teacher.entity.Teacourse;
import com.trainingmanagesys.web.teacher.dao.TeacourseMapper;
import com.trainingmanagesys.web.teacher.service.ITeacourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.teacher.vo.TeacourseVO;
import com.trainingmanagesys.web.user.entity.User;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
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
public class TeacourseServiceImpl extends ServiceImpl<TeacourseMapper, Teacourse> implements ITeacourseService {

    private void checkTeacourseExistence(Long tcSerial){
        if (getTeaCourse(tcSerial) == null)
            throw new APIException("该教师课程不存在");
    }


    @Override
    public String addTeaCourse(Teacourse teacourse) {
        String result = "添加教师课程失败";
        int code = baseMapper.insert(teacourse);
        if (code == 1)
            result = "添加教师课程成功";
        return result;
    }

    @Override
    public String updateTeaCourse(Teacourse teacourse) {
        checkTeacourseExistence(teacourse.getTcSerial());
        String result = "更新教师课程失败";
        int code = baseMapper.updateById(teacourse);
        if (code == 1)
            result = "更新教师课程成功";
        return result;
    }

    @Override
    public String deleteTeaCourse(Long tcSerial) {
        checkTeacourseExistence(tcSerial);
        String result = "删除教师课程失败";
        int code = baseMapper.deleteById(tcSerial);
        if (code == 1)
            result = "删除教师课程成功";
        return result;
    }

    @Override
    public Teacourse getTeaCourse(Long tcSerial) {
        Teacourse teacourse = baseMapper.selectById(tcSerial);
        if (teacourse.getEnable() != BaseConst.DATA_ENABLE) {
            return null;
        } else {
            return teacourse;
        }
    }

    @Override
    public List<Teacourse> listTeaCourse(TeacourseVO teacourseVO) {
        QueryWrapper<Teacourse> queryWrapper = new QueryWrapper<>();
        if (teacourseVO.getTeacherId() != null) queryWrapper.eq("teacher_id", teacourseVO.getTeacherId());
        if (teacourseVO.getCourseCode() != null) queryWrapper.eq("course_code", teacourseVO.getCourseCode());
        if (teacourseVO.getClassCode() != null) queryWrapper.eq("class_code", teacourseVO.getClassCode());
        if (teacourseVO.getPercentageMax() != null) queryWrapper.le("percentage", teacourseVO.getPercentageMax());
        if (teacourseVO.getPercentageMin() != null) queryWrapper.ge("percentage", teacourseVO.getPercentageMin());
        if (teacourseVO.getRemainMax() != null) queryWrapper.le("remain", teacourseVO.getRemainMax());
        if (teacourseVO.getRemainMin() != null) queryWrapper.ge("remain", teacourseVO.getRemainMin());
        if (teacourseVO.getEnable() != null) {
            queryWrapper.eq("enable", teacourseVO.getEnable());
        } else {
            queryWrapper.eq("enable", BaseConst.DATA_ENABLE);
        }
        if (teacourseVO.getLimit() != null) queryWrapper.last(" limit " + teacourseVO.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Teacourse> pagedListTeaCourse(TeacourseVO teacourseVO) {
        QueryWrapper<Teacourse> queryWrapper = new QueryWrapper<>();
        if (teacourseVO.getTeacherId() != null) queryWrapper.eq("teacher_id", teacourseVO.getTeacherId());
        if (teacourseVO.getCourseCode() != null) queryWrapper.eq("course_code", teacourseVO.getCourseCode());
        if (teacourseVO.getClassCode() != null) queryWrapper.eq("class_code", teacourseVO.getClassCode());
        if (teacourseVO.getPercentageMax() != null) queryWrapper.le("percentage", teacourseVO.getPercentageMax());
        if (teacourseVO.getPercentageMin() != null) queryWrapper.ge("percentage", teacourseVO.getPercentageMin());
        if (teacourseVO.getRemainMax() != null) queryWrapper.le("remain", teacourseVO.getRemainMax());
        if (teacourseVO.getRemainMin() != null) queryWrapper.ge("remain", teacourseVO.getRemainMin());
        if (teacourseVO.getEnable() != null) {
            queryWrapper.eq("enable", teacourseVO.getEnable());
        } else {
            queryWrapper.eq("enable", BaseConst.DATA_ENABLE);
        }
        if (teacourseVO.getLimit() != null) queryWrapper.last(" limit " + teacourseVO.getLimit());

        Page<Teacourse> page = new Page<>();
        page.setCurrent(teacourseVO.getCurrentPage());
        page.setSize(teacourseVO.getPageSize());
        IPage<Teacourse> pagedList = baseMapper.selectPage(page, queryWrapper);
        return pagedList;
    }

    @Override
    public List<Clazz> listClazzByTeacherID(@NotNull(message = "请指明老师ID") Long teacherId) {
        return baseMapper.listClazzByTeacherID(teacherId);
    }

    @Override
    public List<Course> listCourseByTeacherID(@NotNull(message = "请指明老师ID") Long teacherId) {
        return baseMapper.listCourseByTeacherID(teacherId);
    }
}
