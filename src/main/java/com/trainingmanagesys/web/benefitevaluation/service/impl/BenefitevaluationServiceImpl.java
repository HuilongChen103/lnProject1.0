package com.trainingmanagesys.web.benefitevaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.benefitevaluation.entity.Benefitevaluation;
import com.trainingmanagesys.web.benefitevaluation.dao.BenefitevaluationMapper;
import com.trainingmanagesys.web.benefitevaluation.service.IBenefitevaluationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.benefitevaluation.vo.AddBenefitevaluationVO;
import com.trainingmanagesys.web.benefitevaluation.vo.BenefitevaluationVO;
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
public class BenefitevaluationServiceImpl extends ServiceImpl<BenefitevaluationMapper, Benefitevaluation> implements IBenefitevaluationService {

    private void checkBenefitevaluationExistence(Long benefitSerial){
        if (getBenefitevaluation(benefitSerial) == null)
            throw new APIException("该绩效审核不存在");
    }

    @Override
    public Long addBenefitevaluation(AddBenefitevaluationVO vo) {
        Benefitevaluation benefitevaluation = new Benefitevaluation();
        benefitevaluation.setStuffId(vo.getStuffId());
        // 新建的时候不能只输入月份，否则报错
        if (vo.getYear() == null && vo.getMonth() != null)
            throw new APIException("请指明年份，不能只输入月份");
        benefitevaluation.setYear(vo.getYear());
        benefitevaluation.setMonth(vo.getMonth());
        benefitevaluation.setBenefit(vo.getBenefit());
        benefitevaluation.setAssessment(vo.getAssessment());
        baseMapper.insert(benefitevaluation);
        return benefitevaluation.getBenefitSerial();
    }

    @Override
    public String updateBenefitevaluation(Benefitevaluation benefitevaluation) {
        checkBenefitevaluationExistence(benefitevaluation.getBenefitSerial());
        // 更新的时候存在更新一个没有年份的对象，但是给予他的是月份
        Benefitevaluation tempBenef = getBenefitevaluation(benefitevaluation.getBenefitSerial());
        if (tempBenef.getYear() == null && benefitevaluation.getMonth() != null)
            throw new APIException("请声明年份，不能在没有年份的情况下单独声明月份");
        String result = "更新绩效审核失败";
        int code = baseMapper.updateById(benefitevaluation);
        if (code == 1)
            result = "更新绩效审核成功";
        return result;
    }

    @Override
    public String deleteBenefitevaluation(Long benefitSerial) {
        checkBenefitevaluationExistence(benefitSerial);
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

    @Override
    public List<Benefitevaluation> listBenefitevaluation(BenefitevaluationVO benefitevaluationVO) {
        QueryWrapper<Benefitevaluation> queryWrapper = new QueryWrapper<>();

        if (benefitevaluationVO.getStuffId() != null) queryWrapper.eq("stuff_id", benefitevaluationVO.getStuffId());
        if (benefitevaluationVO.getYear() != null){
            queryWrapper.eq("year", benefitevaluationVO.getYear());
            if (benefitevaluationVO.getMonth() != null)
                queryWrapper.eq("month", benefitevaluationVO.getMonth());
        }
        if (benefitevaluationVO.getBenefitMax() != null) queryWrapper.le("benefit", benefitevaluationVO.getBenefitMax());
        if (benefitevaluationVO.getBenefitMin() != null) queryWrapper.ge("benefit", benefitevaluationVO.getBenefitMin());
        if (benefitevaluationVO.getAssessment() != null) queryWrapper.eq("assessment", benefitevaluationVO.getAssessment());
        if (benefitevaluationVO.getLimit() != null) queryWrapper.last(" limit " + benefitevaluationVO.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Benefitevaluation> pagedListBenefitevaluation(BenefitevaluationVO benefitevaluationVO) {
        QueryWrapper<Benefitevaluation> queryWrapper = new QueryWrapper<>();

        if (benefitevaluationVO.getStuffId() != null) queryWrapper.eq("stuff_id", benefitevaluationVO.getStuffId());
        if (benefitevaluationVO.getYear() != null){
            queryWrapper.eq("year", benefitevaluationVO.getYear());
            if (benefitevaluationVO.getMonth() != null)
                queryWrapper.eq("month", benefitevaluationVO.getMonth());
        }
        if (benefitevaluationVO.getBenefitMax() != null) queryWrapper.le("benefit", benefitevaluationVO.getBenefitMax());
        if (benefitevaluationVO.getBenefitMin() != null) queryWrapper.ge("benefit", benefitevaluationVO.getBenefitMin());
        if (benefitevaluationVO.getAssessment() != null) queryWrapper.eq("assessment", benefitevaluationVO.getAssessment());
        if (benefitevaluationVO.getLimit() != null) queryWrapper.last(" limit " + benefitevaluationVO.getLimit());


        Page<Benefitevaluation> page = new Page<>();
        page.setCurrent(benefitevaluationVO.getCurrentPage());
        page.setSize(benefitevaluationVO.getPageSize());
        IPage<Benefitevaluation> pagedList = baseMapper.selectPage(page, queryWrapper);
        return pagedList;
    }
}
