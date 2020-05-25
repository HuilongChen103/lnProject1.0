package com.trainingmanagesys.web.homework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.homework.entity.Homework;
import com.trainingmanagesys.web.homework.dao.HomeworkMapper;
import com.trainingmanagesys.web.homework.service.IHomeworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.homework.vo.HomeworkVO;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private void checkHomeworkExistence(Long hwSerial){
        if (getHomework(hwSerial) == null)
            throw new APIException("该作业不存在");
    }

    @Override
    public Long addHomework(Homework homework) {
        baseMapper.insert(homework);
        return homework.getHwSerial();
    }

    @Override
    public String updateHomework(Homework homework) {
        checkHomeworkExistence(homework.getHwSerial());
        String result = "更新作业失败";
        int code = baseMapper.updateById(homework);
        if (code == 1)
            result = "更新作业成功";
        return result;
    }

    @Override
    public String deleteHomework(Long hwSerial) {
        checkHomeworkExistence(hwSerial);
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

    @Override
    public List<Homework> listHomework(HomeworkVO vo) {
        QueryWrapper<Homework> queryWrapper = new QueryWrapper<>();
        if (vo.getArrangeSerial() != null) queryWrapper.eq("arrange_serail", vo.getArrangeSerial());
        if (vo.getStudentId() != null) queryWrapper.eq("student_id", vo.getStudentId());
        if (vo.getHwFile() != null) queryWrapper.eq("hw_file", vo.getHwFile());
        if (vo.getGradeMax() != null) queryWrapper.le("grade", vo.getGradeMax());
        if (vo.getGradeMin() != null) queryWrapper.ge("grade", vo.getGradeMin());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Homework> pagedListHomework(HomeworkVO vo) {
        QueryWrapper<Homework> queryWrapper = new QueryWrapper<>();
        if (vo.getArrangeSerial() != null) queryWrapper.eq("arrange_serail", vo.getArrangeSerial());
        if (vo.getStudentId() != null) queryWrapper.eq("student_id", vo.getStudentId());
        if (vo.getHwFile() != null) queryWrapper.eq("hw_file", vo.getHwFile());
        if (vo.getGradeMax() != null) queryWrapper.le("grade", vo.getGradeMax());
        if (vo.getGradeMin() != null) queryWrapper.ge("grade", vo.getGradeMin());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());

        Page<Homework> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getPageSize());
        IPage<Homework> pagedList =baseMapper.selectPage(page, queryWrapper);
        return pagedList;
    }
}
