package com.trainingmanagesys.web.benefitevaluation.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.benefitevaluation.entity.Benefitevaluation;
import com.trainingmanagesys.web.benefitevaluation.service.IBenefitevaluationService;
import com.trainingmanagesys.web.benefitevaluation.vo.AddBenefitevaluationVO;
import com.trainingmanagesys.web.benefitevaluation.vo.BenefitevaluationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoying
 * @since 2020-05-18
 */
@Api(value = "绩效审核", tags = {"绩效审核接口"})
@RestController
@RequestMapping("/benefitevaluation")
public class BenefitevaluationController {

    @Autowired
    IBenefitevaluationService benefitevaluationService;

    @ApiOperation(value = "添加绩效审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuffId", value = "员工id（职员、教师）", dataType = "Long", required = false),
            @ApiImplicitParam(name = "year", value = "年份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "month", value = "月份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "benefit", value = "效益（具体金额）", dataType = "Long", required = false),
            @ApiImplicitParam(name = "assessment", value = "考评（文字说明）", dataType = "String", required = false)
    })
    @PostMapping("/addBenefitevaluation")
    public Long addBenefitevaluation(@RequestBody AddBenefitevaluationVO benefitevaluation){
        return benefitevaluationService.addBenefitevaluation(benefitevaluation);
    }

    @ApiOperation(value = "修改绩效审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "benefitSerial", value = "绩效考评流水号", dataType = "Long", required = true),
            @ApiImplicitParam(name = "stuffId", value = "员工id（职员、教师）", dataType = "Long", required = false),
            @ApiImplicitParam(name = "year", value = "年份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "month", value = "月份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "benefit", value = "效益（具体金额）", dataType = "Long", required = false),
            @ApiImplicitParam(name = "assessment", value = "考评（文字说明）", dataType = "String", required = false)
    })
    @PostMapping("/updateBenefitevaluation")
    public String updateBenefitevaluation(@RequestBody @Validated Benefitevaluation benefitevaluation){
        return benefitevaluationService.updateBenefitevaluation(benefitevaluation);
    }

    @ApiOperation(value = "删除绩效审核")
    @ApiImplicitParam(name = "benefitSerial", value = "绩效考评流水号", dataType = "Long", required = true)
    @PostMapping("/deleteBenefitevaluation")
    public String deleteBenefitevaluation(@NotNull(message = "请指明绩效考评流水号") Long benefitSerial){
        return benefitevaluationService.deleteBenefitevaluation(benefitSerial);
    }

    @ApiOperation(value = "获取绩效审核")
    @ApiImplicitParam(name = "benefitSerial", value = "绩效考评流水号", dataType = "Long", required = true)
    @PostMapping("/getBenefitevaluation")
    public Benefitevaluation getBenefitevaluation(@NotNull(message = "请指明绩效考评流水号") Long benefitSerial){
        return benefitevaluationService.getBenefitevaluation(benefitSerial);
    }

    @ApiOperation(value = "列绩效审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuffId", value = "员工id（职员、教师）", dataType = "Long", required = false),
            @ApiImplicitParam(name = "year", value = "年份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "month", value = "月份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "benefitMax", value = "效益（具体金额）最大值", dataType = "Long", required = false),
            @ApiImplicitParam(name = "benefitMin", value = "效益（具体金额）最小值", dataType = "Long", required = false),
            @ApiImplicitParam(name = "assessment", value = "考评（文字说明）", dataType = "String", required = false),
            @ApiImplicitParam(name = "limit", value = "考评（文字说明）", dataType = "Integer", required = false)
    })
    @PostMapping("/listBenefitevaluation")
    public List<Benefitevaluation> listBenefitevaluation(@RequestBody @Validated BenefitevaluationVO benefitevaluationVO){
        return benefitevaluationService.listBenefitevaluation(benefitevaluationVO);
    }

    @ApiOperation(value = "分页列绩效审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuffId", value = "员工id（职员、教师）", dataType = "Long", required = false),
            @ApiImplicitParam(name = "year", value = "年份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "month", value = "月份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "benefitMax", value = "效益（具体金额）最大值", dataType = "Long", required = false),
            @ApiImplicitParam(name = "benefitMin", value = "效益（具体金额）最小值", dataType = "Long", required = false),
            @ApiImplicitParam(name = "assessment", value = "考评（文字说明）", dataType = "String", required = false),
            @ApiImplicitParam(name = "limit", value = "考评（文字说明）", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", dataType = "Integer", required = false),
    })
    @PostMapping("/pagedListBenefitevaluation")
    public IPage<Benefitevaluation> pagedListBenefitevaluation(@RequestBody @Validated BenefitevaluationVO benefitevaluationVO){
        return benefitevaluationService.pagedListBenefitevaluation(benefitevaluationVO);
    }
}
