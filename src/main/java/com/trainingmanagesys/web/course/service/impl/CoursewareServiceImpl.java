package com.trainingmanagesys.web.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.course.entity.Courseware;
import com.trainingmanagesys.web.course.dao.CoursewareMapper;
import com.trainingmanagesys.web.course.service.ICoursewareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.course.vo.CoursewareVO;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private Courseware checkCoursewareExistence(Long coursewareSerial){
        Courseware tempCourseware = getCourseware(coursewareSerial);
        if (tempCourseware == null)
            throw new APIException("该课件不存在");
        return tempCourseware;
    }

    @Override
    public Long addCourseware(Courseware courseware) {
        baseMapper.insert(courseware);
        return courseware.getCoursewareSerial();
    }

    @Override
    public String updateCourseware(Courseware courseware) {
        checkCoursewareExistence(courseware.getCoursewareSerial());
        String result = "更新课件失败";
        int code = baseMapper.updateById(courseware);
        if (code == 1)
            result = "更新课件成功";
        return result;
    }

    @Override
    public String deleteCourseware(Long coursewareSerial) {
        checkCoursewareExistence(coursewareSerial);
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

    @Override
    public List<Courseware> listCourseware(CoursewareVO vo) {
        QueryWrapper<Courseware> queryWrapper = new QueryWrapper<>();
        if (vo.getClassCode() != null) queryWrapper.like("class_code", vo.getClassCode());
        if (vo.getTitle() != null) queryWrapper.like("title", vo.getTitle());
        if (vo.getFileSerial() != null) queryWrapper.eq("file_serial", vo.getFileSerial());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Courseware> pagedListCourseware(CoursewareVO vo) {
        QueryWrapper<Courseware> queryWrapper = new QueryWrapper<>();
        if (vo.getClassCode() != null) queryWrapper.like("class_code", vo.getClassCode());
        if (vo.getTitle() != null) queryWrapper.like("title", vo.getTitle());
        if (vo.getFileSerial() != null) queryWrapper.eq("file_serial", vo.getFileSerial());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());

        Page<Courseware> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getPageSize());
        IPage<Courseware> pagedList = baseMapper.selectPage(page, queryWrapper);
        return pagedList;
    }
}
