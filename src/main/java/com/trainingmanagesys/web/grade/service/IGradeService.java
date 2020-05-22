package com.trainingmanagesys.web.grade.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.grade.entity.Grade;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.grade.vo.GradeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
public interface IGradeService extends IService<Grade> {

    Long addGrade(Grade grade);

    String updateGrade(Grade grade);

    String deleteGrade(Long gradeSerial);

    Grade getGrade(Long gradeSerial);

    List<Grade> listGrade(GradeVO vo);

    IPage<Grade> pagedListGrade(GradeVO vo);
}
