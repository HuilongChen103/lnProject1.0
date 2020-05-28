package com.trainingmanagesys.web.student.dao;

import com.trainingmanagesys.web.clazz.entity.Clazz;
import com.trainingmanagesys.web.course.entity.Course;
import com.trainingmanagesys.web.student.entity.Stucourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoying
 * @since 2020-05-13
 */
public interface StucourseMapper extends BaseMapper<Stucourse> {

    List<Course> listCourseByStuId(@Param("studentId") Long studentId);

    List<Clazz> listClazzByStuId(@Param("studentId") Long studentId);
}
