package com.trainingmanagesys.web.student.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.course.entity.Course;
import com.trainingmanagesys.web.student.entity.Stucourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.student.vo.StucourseVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-13
 */
public interface IStucourseService extends IService<Stucourse> {

    String addStuCourse(Stucourse stucourse);

    String updateStuCourse(Stucourse stucourse);

    String deleteStuCourse(Long scSerial);

    Stucourse getStuCourse(Long scSerial);

    List<Stucourse> listStuCourse(StucourseVO stucourseVO);

    IPage<Stucourse> pagedListStuCourse(StucourseVO stucourseVO);

    List<Course> listCourseByStuID(Long studentId);
}
