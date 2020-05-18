package com.trainingmanagesys.web.benefitevaluation.service.impl;

import com.trainingmanagesys.web.benefitevaluation.entity.Benefitevaluation;
import com.trainingmanagesys.web.benefitevaluation.dao.BenefitevaluationMapper;
import com.trainingmanagesys.web.benefitevaluation.service.IBenefitevaluationService;
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
public class BenefitevaluationServiceImpl extends ServiceImpl<BenefitevaluationMapper, Benefitevaluation> implements IBenefitevaluationService {

    @Override
    public Long addBenefitevaluation(Benefitevaluation benefitevaluation) {
        baseMapper.insert(benefitevaluation);
        return benefitevaluation.getBenefitSerial();
    }

    @Override
    public String updateBenefitevaluation(Benefitevaluation benefitevaluation) {
        String result = "更新绩效审核失败";
        int code = baseMapper.updateById(benefitevaluation);
        if (code == 1)
            result = "更新绩效审核成功";
        return result;
    }

    @Override
    public String deleteBenefitevaluation(Long benefitSerial) {
        String result = "删除绩效审核失败";
        int code = baseMapper.deleteById(benefitSerial);
        if (code == 1)
            result = "删除绩效审核成功";
        return result;
    }

    @Override
    public Benefitevaluation getBenefitevaluation(Long benefitSerial) {
        return baseMapper.selectById(benefitSerial);
    }
}
