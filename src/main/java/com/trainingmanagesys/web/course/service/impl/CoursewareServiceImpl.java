package com.trainingmanagesys.web.course.service.impl;

import com.trainingmanagesys.web.course.entity.Courseware;
import com.trainingmanagesys.web.course.dao.CoursewareMapper;
import com.trainingmanagesys.web.course.service.ICoursewareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
@Service
public class CoursewareServiceImpl extends ServiceImpl<CoursewareMapper, Courseware> implements ICoursewareService {

    @Override
    public Long addCourseware(Courseware courseware) {
        baseMapper.insert(courseware);
        return courseware.getCoursewareSerial();
    }

    @Override
    public String updateCourseware(Courseware courseware) {
        String result = "更新课件失败";
        int code = baseMapper.updateById(courseware);
        if (code == 1)
            result = "更新课件成功";
        return result;
    }

    @Override
    public String deleteCourseware(Long coursewareSerial) {
        String result = "删除课件失败";
        int code = baseMapper.deleteById(coursewareSerial);
        if (code == 1)
            result = "删除课件成功";
        return result;
    }

    @Override
    public Courseware getCourseware(Long coursewareSerial) {
        return baseMapper.selectById(coursewareSerial);
    }
}
