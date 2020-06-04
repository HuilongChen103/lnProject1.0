package com.trainingmanagesys.web.teacher.dao;

import com.trainingmanagesys.web.clazz.entity.Clazz;
import com.trainingmanagesys.web.course.entity.Course;
import com.trainingmanagesys.web.teacher.entity.Teacourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoying
 * @since 2020-05-13
 */
public interface TeacourseMapper extends BaseMapper<Teacourse> {

    List<Clazz> listClazzByTeacherID(@Param("teacherId") Long teacherId);

    List<Course> listCourseByTeacherID(@Param("teacherId") Long teacherId);
}
