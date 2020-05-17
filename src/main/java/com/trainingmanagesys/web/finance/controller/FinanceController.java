package com.trainingmanagesys.web.finance.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.finance.entity.Finance;
import com.trainingmanagesys.web.finance.service.IFinanceService;
import com.trainingmanagesys.web.finance.vo.FinanceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoying
 * @since 2020-05-12
 */
@Api(value = "财务", tags = {"财务操作接口"})
@RestController
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    IFinanceService financeService;

    @ApiOperation(value = "添加财务信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "financeCode", value = "开支流水号", dataType = "String", required = true),
        @ApiImplicitParam(name = "inOut", value = "声明收入支出：'EXP','REV'", dataType = "String", required = true),
        @ApiImplicitParam(name = "picId", value = "负责人uid", dataType = "Long", required = true),
        @ApiImplicitParam(name = "payAccount", value = "付款账户", dataType = "String", required = false),
        @ApiImplicitParam(name = "receiveAccount", value = "收款账户", dataType = "String", required = false),
        @ApiImplicitParam(name = "tradeMethod", value = "付款方式：支付宝、微信、银行转账", dataType = "String", required = false),
        @ApiImplicitParam(name = "amount", value = "金额大小", dataType = "Long", required = false),
        @ApiImplicitParam(name = "date", value = "转账日期", dataType = "Date", required = false),
        @ApiImplicitParam(name = "category", value = "类目：员工薪水、器材采购、水电租金、广告宣传", dataType = "String", required = false),
        @ApiImplicitParam(name = "comment", value = "备注", dataType = "String", required = false)
    })
    @PostMapping("/addFinance")
    public String addFinance(@RequestBody @Validated Finance finance){
        return financeService.addFinance(finance);
    }

    @ApiOperation(value = "添加财务信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "financeCode", value = "开支流水号", dataType = "String", required = true),
        @ApiImplicitParam(name = "inOut", value = "声明收入支出：'EXP','REV'", dataType = "String", required = false),
        @ApiImplicitParam(name = "picId", value = "负责人uid", dataType = "Long", required = false),
        @ApiImplicitParam(name = "payAccount", value = "付款账户", dataType = "String", required = false),
        @ApiImplicitParam(name = "receiveAccount", value = "收款账户", dataType = "String", required = false),
        @ApiImplicitParam(name = "tradeMethod", value = "付款方式：支付宝、微信、银行转账", dataType = "String", required = false),
        @ApiImplicitParam(name = "amount", value = "金额大小", dataType = "Long", required = false),
        @ApiImplicitParam(name = "date", value = "转账日期", dataType = "Date", required = false),
        @ApiImplicitParam(name = "category", value = "类目：员工薪水、器材采购、水电租金、广告宣传", dataType = "String", required = false),
        @ApiImplicitParam(name = "comment", value = "备注", dataType = "String", required = false)
    })
    @PostMapping("/updateFinance")
    public String updateFinance(@RequestBody @Validated Finance finance){
        return financeService.updateFinance(finance);
    }

    @ApiOperation(value = "根据开支流水号获取财务记录")
    @ApiImplicitParam(name = "financeCode", value = "开支流水号", dataType = "String", required = true)
    @GetMapping("/getFinance")
    public Finance getFinance(@NotNull(message = "请指明开支流水号") String financeCode){
        return financeService.getFinance(financeCode);
    }

    @ApiOperation(value = "根据开支流水号删除财务记录")
    @ApiImplicitParam(name = "financeCode", value = "开支流水号", dataType = "String", required = true)
    @GetMapping("/deleteFinance")
    public String deleteFinance(@NotNull(message = "请指明开支流水号") String financeCode){
        return financeService.deleteFinance(financeCode);
    }

    @ApiOperation(value = "列财务信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inOut", value = "声明收入支出：'EXP','REV'", dataType = "String", required = false),
            @ApiImplicitParam(name = "picId", value = "负责人uid", dataType = "Long", required = false),
            @ApiImplicitParam(name = "payAccount", value = "付款账户", dataType = "String", required = false),
            @ApiImplicitParam(name = "receiveAccount", value = "收款账户", dataType = "String", required = false),
            @ApiImplicitParam(name = "tradeMethod", value = "付款方式：支付宝、微信、银行转账", dataType = "String", required = false),
            @ApiImplicitParam(name = "minAmount", value = "最小金额", dataType = "Long", required = false),
            @ApiImplicitParam(name = "maxAmount", value = "最大金额", dataType = "Long", required = false),
            @ApiImplicitParam(name = "date", value = "转账日期", dataType = "Date", required = false),
            @ApiImplicitParam(name = "category", value = "类目：员工薪水、器材采购、水电租金、广告宣传", dataType = "String", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false)
    })
    @PostMapping("/listFinance")
    public List<Finance> listFinance(@RequestBody FinanceVO finance){
        return financeService.listFinance(finance);
    }

    @ApiOperation(value = "分页列财务信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inOut", value = "声明收入支出：'EXP','REV'", dataType = "String", required = false),
            @ApiImplicitParam(name = "picId", value = "负责人uid", dataType = "Long", required = false),
            @ApiImplicitParam(name = "payAccount", value = "付款账户", dataType = "String", required = false),
            @ApiImplicitParam(name = "receiveAccount", value = "收款账户", dataType = "String", required = false),
            @ApiImplicitParam(name = "tradeMethod", value = "付款方式：支付宝、微信、银行转账", dataType = "String", required = false),
            @ApiImplicitParam(name = "minAmount", value = "最小金额", dataType = "Long", required = false),
            @ApiImplicitParam(name = "maxAmount", value = "最大金额", dataType = "Long", required = false),
            @ApiImplicitParam(name = "date", value = "转账日期", dataType = "Date", required = false),
            @ApiImplicitParam(name = "category", value = "类目：员工薪水、器材采购、水电租金、广告宣传", dataType = "String", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", dataType = "Integer", required = true),
    })
    @PostMapping("/pagedListFinance")
    public IPage<Finance> pagedListFinance(@RequestBody @Validated(FinanceVO.basicNotNullGroup.class) FinanceVO finance){
        return financeService.pagedListFinance(finance);
    }
}
