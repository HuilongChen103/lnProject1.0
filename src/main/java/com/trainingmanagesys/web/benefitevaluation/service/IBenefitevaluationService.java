package com.trainingmanagesys.web.benefitevaluation.service;

import com.trainingmanagesys.web.benefitevaluation.entity.Benefitevaluation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-18
 */
public interface IBenefitevaluationService extends IService<Benefitevaluation> {

    Long addBenefitevaluation(Benefitevaluation benefitevaluation);

    String updateBenefitevaluation(Benefitevaluation benefitevaluation);

    String deleteBenefitevaluation(Long benefitSerial);

    Benefitevaluation getBenefitevaluation(Long benefitSerial);
}
