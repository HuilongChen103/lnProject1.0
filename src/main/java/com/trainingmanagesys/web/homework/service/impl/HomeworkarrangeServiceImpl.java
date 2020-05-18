package com.trainingmanagesys.web.homework.service.impl;

import com.trainingmanagesys.web.homework.entity.Homework;
import com.trainingmanagesys.web.homework.entity.Homeworkarrange;
import com.trainingmanagesys.web.homework.dao.HomeworkarrangeMapper;
import com.trainingmanagesys.web.homework.service.IHomeworkarrangeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-18
 */
@Service
public class HomeworkarrangeServiceImpl extends ServiceImpl<HomeworkarrangeMapper, Homeworkarrange> implements IHomeworkarrangeService {

    @Override
    public Long addHomeworkarrange(Homeworkarrange homeworkarrange) {
        baseMapper.insert(homeworkarrange);
        return homeworkarrange.getArrangeSerial();
    }

    @Override
    public String updateHomeworkarrange(Homeworkarrange homeworkarrange) {
        String result = "更新作业安排失败";
        int code = baseMapper.updateById(homeworkarrange);
        if (code == 1)
            result = "更新作业安排成功";
        return result;
    }

    @Override
    public String deleteHomeworkarrange(Long arrangeSerial) {
        String result = "删除作业安排失败";
        int code = baseMapper.deleteById(arrangeSerial);
        if (code == 1)
            result = "删除作业安排成功";
        return result;
    }

    @Override
    public Homeworkarrange getHomeworkarrange(Long arrangeSerial) {
        return baseMapper.selectById(arrangeSerial);
    }
}
