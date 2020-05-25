package com.trainingmanagesys.web.homework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.homework.entity.Homework;
import com.trainingmanagesys.web.homework.entity.Homeworkarrange;
import com.trainingmanagesys.web.homework.dao.HomeworkarrangeMapper;
import com.trainingmanagesys.web.homework.service.IHomeworkarrangeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.homework.vo.HomeworkarrangeVO;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private void checkHomeworkarrangeExistence(Long arrangeSerial){
        if (getHomeworkarrange(arrangeSerial) == null)
            throw new APIException("该作业安排不存在");
    }

    @Override
    public Long addHomeworkarrange(Homeworkarrange homeworkarrange) {
        baseMapper.insert(homeworkarrange);
        return homeworkarrange.getArrangeSerial();
    }

    @Override
    public String updateHomeworkarrange(Homeworkarrange homeworkarrange) {
        checkHomeworkarrangeExistence(homeworkarrange.getArrangeSerial());
        String result = "更新作业安排失败";
        int code = baseMapper.updateById(homeworkarrange);
        if (code == 1)
            result = "更新作业安排成功";
        return result;
    }

    @Override
    public String deleteHomeworkarrange(Long arrangeSerial) {
        checkHomeworkarrangeExistence(arrangeSerial);
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

    @Override
    public List<Homeworkarrange> listHomeworkarrange(HomeworkarrangeVO vo) {
        QueryWrapper<Homeworkarrange> queryWrapper = new QueryWrapper<>();
        if (vo.getClassCode() != null) queryWrapper.eq("class_code", vo.getClassCode());
        if (vo.getArrangeFile() != null) queryWrapper.eq("arrange_file", vo.getArrangeFile());
        if (vo.getDeadline() != null) queryWrapper.eq("deadline", vo.getDeadline());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Homeworkarrange> pagedListHomeworkarrange(HomeworkarrangeVO vo) {
        QueryWrapper<Homeworkarrange> queryWrapper = new QueryWrapper<>();
        if (vo.getClassCode() != null) queryWrapper.eq("class_code", vo.getClassCode());
        if (vo.getArrangeFile() != null) queryWrapper.eq("arrange_file", vo.getArrangeFile());
        if (vo.getDeadline() != null) queryWrapper.eq("deadline", vo.getDeadline());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());

        Page<Homeworkarrange> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getPageSize());
        return baseMapper.selectPage(page, queryWrapper);
    }
}
