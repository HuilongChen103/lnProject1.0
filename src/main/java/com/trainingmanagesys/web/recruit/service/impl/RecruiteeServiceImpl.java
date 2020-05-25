package com.trainingmanagesys.web.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.recruit.entity.Recruitee;
import com.trainingmanagesys.web.recruit.dao.RecruiteeMapper;
import com.trainingmanagesys.web.recruit.service.IRecruiteeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.recruit.vo.RecruiteeVO;
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
public class RecruiteeServiceImpl extends ServiceImpl<RecruiteeMapper, Recruitee> implements IRecruiteeService {

    private void checkRecruiteeExistence(String recruiteeCode){
        if (getRecruitee(recruiteeCode) == null)
            throw new APIException("该受招募者不存在");
    }

    @Override
    public String addRecruitee(Recruitee recruitee) {
        if (getRecruitee(recruitee.getRecruiteeCode()) != null)
            throw new APIException("该受招募者已存在");
        baseMapper.insert(recruitee);
        return recruitee.getRecruiteeCode();
    }

    @Override
    public String updateRecruitee(Recruitee recruitee) {
        checkRecruiteeExistence(recruitee.getRecruiteeCode());
        String result = "更新受招募者失败";
        int code = baseMapper.updateById(recruitee);
        if (code == 1)
            result = "更新受招募者成功";
        return result;
    }

    @Override
    public String deleteRecruitee(String recruiteeCode) {
        checkRecruiteeExistence(recruiteeCode);
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

    @Override
    public List<Recruitee> listRecruitee(RecruiteeVO vo) {
        QueryWrapper<Recruitee> queryWrapper = new QueryWrapper<>();

        if (vo.getRecruitCode() != null) queryWrapper.eq("recruit_code", vo.getRecruitCode());
        if (vo.getCatagory() != null ) queryWrapper.eq("category", vo.getCatagory());
        if (vo.getAuditSerial() != null) queryWrapper.eq("audit_serial", vo.getAuditSerial());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Recruitee> pagedListRecruitee(RecruiteeVO vo) {
        QueryWrapper<Recruitee> queryWrapper = new QueryWrapper<>();

        if (vo.getRecruitCode() != null) queryWrapper.eq("recruit_code", vo.getRecruitCode());
        if (vo.getCatagory() != null ) queryWrapper.eq("category", vo.getCatagory());
        if (vo.getAuditSerial() != null) queryWrapper.eq("audit_serial", vo.getAuditSerial());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());

        Page<Recruitee> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getPageSize());
        return baseMapper.selectPage(page, queryWrapper);
    }
}
