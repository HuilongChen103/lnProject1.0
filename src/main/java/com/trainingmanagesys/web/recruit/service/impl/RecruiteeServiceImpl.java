package com.trainingmanagesys.web.recruit.service.impl;

import com.trainingmanagesys.web.recruit.entity.Recruitee;
import com.trainingmanagesys.web.recruit.dao.RecruiteeMapper;
import com.trainingmanagesys.web.recruit.service.IRecruiteeService;
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
public class RecruiteeServiceImpl extends ServiceImpl<RecruiteeMapper, Recruitee> implements IRecruiteeService {

    @Override
    public String addRecruitee(Recruitee recruitee) {
        baseMapper.insert(recruitee);
        return recruitee.getRecruiteeCode();
    }

    @Override
    public String updateRecruitee(Recruitee recruitee) {
        String result = "更新受招募者失败";
        int code = baseMapper.updateById(recruitee);
        if (code == 1)
            result = "更新受招募者成功";
        return result;
    }

    @Override
    public String deleteRecruitee(String recruiteeCode) {
        String result = "删除受招募者失败";
        int code = baseMapper.deleteById(recruiteeCode);
        if (code == 1)
            result = "删除受招募者成功";
        return result;
    }

    @Override
    public Recruitee getRecruitee(String recruiteeCode) {
        return baseMapper.selectById(recruiteeCode);
    }
}
