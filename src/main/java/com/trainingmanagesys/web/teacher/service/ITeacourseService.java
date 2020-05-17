package com.trainingmanagesys.web.teacher.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.teacher.entity.Teacourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.teacher.vo.TeacourseVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-13
 */
public interface ITeacourseService extends IService<Teacourse> {

    String addTeaCourse(Teacourse teacourse);

    String updateTeaCourse(Teacourse teacourse);

    String deleteTeaCourse(Long tcSerial);

    List<Teacourse> listTeaCourse(TeacourseVO teacourseVO);

    IPage<Teacourse> pagedListTeaCourse(TeacourseVO teacourseVO);
}
