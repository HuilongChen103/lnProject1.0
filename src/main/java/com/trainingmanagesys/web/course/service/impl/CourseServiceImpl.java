package com.trainingmanagesys.web.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.course.entity.Course;
import com.trainingmanagesys.web.course.dao.CourseMapper;
import com.trainingmanagesys.web.course.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.course.vo.CourseVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-18
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    private Course checkCourseExistence(String courseCode){
        Course tempCourse = getCourse(courseCode);
        if (tempCourse == null)
            throw new APIException("该课程不存在");
        return tempCourse;
    }

    @Override
    public String addCourse(Course course) {
        if (getCourse(course.getCourseCode()) != null)
            throw new APIException("该课程已存在，请更换课程号");
        baseMapper.insert(course);
        return course.getCourseCode();
    }

    @Override
    public String updateCourse(Course course) {
        checkCourseExistence(course.getCourseCode());
        String result = "更新课程失败";
        int code = baseMapper.updateById(course);
        if (code == 1)
            result = "更新课程成功";
        return result;
    }

    @Override
    public String deleteCourse(String courseCode) {
        checkCourseExistence(courseCode);
        String result = "删除课程失败";
        int code = baseMapper.deleteById(courseCode);
        if (code == 1)
            result = "删除课程成功";
        return result;
    }

    @Override
    public Course getCourse(String courseCode) {
        return baseMapper.selectById(courseCode);
    }

    @Override
    public List<Course> listCourse(CourseVO courseVO) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if (courseVO.getCourseName() != null) queryWrapper.like("course_name", courseVO.getCourseName());
        if (courseVO.getStudentMaxMax() != null) queryWrapper.le("student_max", courseVO.getStudentMaxMax());
        if (courseVO.getStudentMaxMin() != null) queryWrapper.ge("student_max", courseVO.getStudentMaxMin());
        if (courseVO.getType() != null) queryWrapper.eq("type", courseVO.getType());
        if (courseVO.getLimit() != null) queryWrapper.last(" limit " + courseVO.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Course> pagedListCourse(CourseVO courseVO) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if (courseVO.getCourseName() != null) queryWrapper.like("course_name", courseVO.getCourseName());
        if (courseVO.getStudentMaxMax() != null) queryWrapper.le("student_max", courseVO.getStudentMaxMax());
        if (courseVO.getStudentMaxMin() != null) queryWrapper.ge("student_max", courseVO.getStudentMaxMin());
        if (courseVO.getType() != null) queryWrapper.eq("type", courseVO.getType());
        if (courseVO.getLimit() != null) queryWrapper.last(" limit " + courseVO.getLimit());

        Page<Course> page = new Page<>();
        page.setCurrent(courseVO.getCurrentPage());
        page.setSize(courseVO.getPageSize());
        IPage<Course> pagedList = baseMapper.selectPage(page, queryWrapper);
        return pagedList;
    }
}
