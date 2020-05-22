package com.trainingmanagesys.web.recruit.service.impl;

import com.trainingmanagesys.web.recruit.entity.Recruit;
import com.trainingmanagesys.web.recruit.dao.RecruitMapper;
import com.trainingmanagesys.web.recruit.service.IRecruitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public String addRecruit(Recruit recruit) {
        baseMapper.insert(recruit);
        return recruit.getRecruitCode();
    }

    @Override
    public String updateRecruit(Recruit recruit) {
        String result = "更新招聘失败";
        int code = baseMapper.updateById(recruit);
        if (code == 1)
            result = "更新招聘成功";
        return result;
    }

    @Override
    public String deleteRecruit(String recruitCode) {
        String result = "删除招聘失败";
        int code = baseMapper.deleteById(recruitCode);
        if (code == 1)
            result = "删除招聘成功";
        return result;
    }

    @Override
    public Recruit getRecruit(String recruitCode) {
        return baseMapper.selectById(recruitCode);
    }
}
