package com.trainingmanagesys.web.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.utils.BaseConst;
import com.trainingmanagesys.web.recruit.entity.Recruit;
import com.trainingmanagesys.web.recruit.dao.RecruitMapper;
import com.trainingmanagesys.web.recruit.service.IRecruitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.recruit.vo.RecruitVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-22
 */
@Service
public class RecruitServiceImpl extends ServiceImpl<RecruitMapper, Recruit> implements IRecruitService {

    private void checkRecruitExistence(String recruitCode){
        Recruit temp = baseMapper.selectById(recruitCode);
        if (temp == null || temp.getEnable() == BaseConst.DATA_DISABLE)
            throw new APIException("该招聘不存在");
    }

    @Override
    public String addRecruit(Recruit recruit) {
        baseMapper.insert(recruit);
        return recruit.getRecruitCode();
    }

    @Override
    public String updateRecruit(Recruit recruit) {
        checkRecruitExistence(recruit.getRecruitCode());
        String result = "更新招聘失败";
        int code = baseMapper.updateById(recruit);
        if (code == 1)
            result = "更新招聘成功";
        return result;
    }

    @Override
    public String deleteRecruit(String recruitCode) {
        checkRecruitExistence(recruitCode);
        String result = "删除招聘失败";
        int code = baseMapper.deleteById(recruitCode);
        if (code == 1)
            result = "删除招聘成功";
        return result;
    }


    @Override
    public Recruit getRecruit(String recruitCode, Integer enable) {
        /**
         * dao层自动处理enable，如果enable为空，则默认其为1
         */
        return baseMapper.getRecruit(recruitCode, enable);
    }

    @Override
    public Recruit getRecruitWithPICId(Long picId, Integer enable) {
        return baseMapper.getRecruitWithPICId(picId, enable);
    }

    @Override
    public List<Recruit> listRecruit(RecruitVO vo) {
        QueryWrapper<Recruit> queryWrapper = new QueryWrapper<>();
        if (vo.getPicId() != null) queryWrapper.eq("PIC_id", vo.getPicId());
        if (vo.getScheduleSerial() != null) queryWrapper.eq("schedule_serial", vo.getScheduleSerial());
        if (vo.getPlace() != null) queryWrapper.like("place", vo.getPlace());
        if (vo.getMethod() != null) queryWrapper.eq("method", vo.getMethod());
        if (vo.getCatagory() != null) queryWrapper.eq("category", vo.getCatagory());
        if (vo.getEnable() != null) queryWrapper.eq("enable", vo.getEnable());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Recruit> pagedListRecruit(RecruitVO vo) {
        QueryWrapper<Recruit> queryWrapper = new QueryWrapper<>();
        if (vo.getPicId() != null) queryWrapper.eq("PIC_id", vo.getPicId());
        if (vo.getScheduleSerial() != null) queryWrapper.eq("schedule_serial", vo.getScheduleSerial());
        if (vo.getPlace() != null) queryWrapper.like("place", vo.getPlace());
        if (vo.getMethod() != null) queryWrapper.eq("method", vo.getMethod());
        if (vo.getCatagory() != null) queryWrapper.eq("category", vo.getCatagory());
        if (vo.getEnable() != null) queryWrapper.eq("enable", vo.getEnable());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());

        Page<Recruit> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getPageSize());
        return baseMapper.selectPage(page, queryWrapper);
    }
}
