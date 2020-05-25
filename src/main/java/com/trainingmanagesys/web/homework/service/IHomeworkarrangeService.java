package com.trainingmanagesys.web.homework.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.homework.entity.Homework;
import com.trainingmanagesys.web.homework.entity.Homeworkarrange;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.homework.vo.HomeworkarrangeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-18
 */
public interface IHomeworkarrangeService extends IService<Homeworkarrange> {

    Long addHomeworkarrange(Homeworkarrange homeworkarrange);

    String updateHomeworkarrange(Homeworkarrange homeworkarrange);

    String deleteHomeworkarrange(Long arrangeSerial);

    Homeworkarrange getHomeworkarrange(Long arrangeSerial);

    List<Homeworkarrange> listHomeworkarrange(HomeworkarrangeVO vo);

    IPage<Homeworkarrange> pagedListHomeworkarrange(HomeworkarrangeVO vo);
}
