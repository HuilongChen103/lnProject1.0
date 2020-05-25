package com.trainingmanagesys.web.homework.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.homework.entity.Homework;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.homework.vo.HomeworkVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-13
 */
public interface IHomeworkService extends IService<Homework> {

    Long addHomework(Homework homework);

    String updateHomework(Homework homework);

    String deleteHomework(Long hwSerial);

    Homework getHomework(Long hwSerial);

    List<Homework> listHomework(HomeworkVO vo);

    IPage<Homework> pagedListHomework(HomeworkVO vo);
}
