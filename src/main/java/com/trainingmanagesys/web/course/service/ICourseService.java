package com.trainingmanagesys.web.course.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.course.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.course.vo.CourseVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-18
 */
public interface ICourseService extends IService<Course> {

    String addCourse(Course course);

    String updateCourse(Course course);

    String deleteCourse(String courseCode);

    Course getCourse(String courseCode);

    List<Course> listCourse(CourseVO courseVO);

    IPage<Course> pagedListCourse(CourseVO courseVO);
}
