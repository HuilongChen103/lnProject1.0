package com.trainingmanagesys.web.benefitevaluation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.benefitevaluation.entity.Benefitevaluation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.benefitevaluation.vo.AddBenefitevaluationVO;
import com.trainingmanagesys.web.benefitevaluation.vo.BenefitevaluationVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-18
 */
public interface IBenefitevaluationService extends IService<Benefitevaluation> {

    Long addBenefitevaluation(AddBenefitevaluationVO addBenefitevaluationVO);

    String updateBenefitevaluation(Benefitevaluation benefitevaluation);

    String deleteBenefitevaluation(Long benefitSerial);

    Benefitevaluation getBenefitevaluation(Long benefitSerial);

    List<Benefitevaluation> listBenefitevaluation(BenefitevaluationVO benefitevaluationVO);

    IPage<Benefitevaluation> pagedListBenefitevaluation(BenefitevaluationVO benefitevaluationVO);
}
