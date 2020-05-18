package com.trainingmanagesys.web.homework.service.impl;

import com.trainingmanagesys.web.homework.entity.Homework;
import com.trainingmanagesys.web.homework.dao.HomeworkMapper;
import com.trainingmanagesys.web.homework.service.IHomeworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-13
 */
@Service
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework> implements IHomeworkService {

    @Override
    public Long addHomework(Homework homework) {
        baseMapper.insert(homework);
        return homework.getHwSerial();
    }

    @Override
    public String updateHomework(Homework homework) {
        String result = "更新作业失败";
        int code = baseMapper.updateById(homework);
        if (code == 1)
            result = "更新作业成功";
        return result;
    }

    @Override
    public String deleteHomework(Long hwSerial) {
        String result = "删除作业失败";
        int code = baseMapper.deleteById(hwSerial);
        if (code == 1)
            result = "删除作业成功";
        return result;
    }

    @Override
    public Homework getHomework(Long hwSerial) {
        return baseMapper.selectById(hwSerial);
    }
}
