package com.trainingmanagesys.web.salary.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.salary.entity.Salary;
import com.trainingmanagesys.web.salary.service.ISalaryService;
import com.trainingmanagesys.web.salary.vo.AddSalaryVO;
import com.trainingmanagesys.web.salary.vo.ListSalaryVO;
import com.trainingmanagesys.web.salary.vo.SalaryVO;
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
 * @since 2020-05-21
 */
@Api(value = "工资流水", tags = {"工资流水操作接口"})
@RestController
@RequestMapping("/salary")
@Validated
public class SalaryController {

    @Autowired
    ISalaryService salaryService;

    @ApiOperation(value = "添加工资流水")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuffId", value = "员工id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "basicSalary", value = "基本工资", dataType = "Double", required = false),
            @ApiImplicitParam(name = "bonus", value = "奖金", dataType = "Double", required = false),
            @ApiImplicitParam(name = "totalSalary", value = "总工资", dataType = "Double", required = false),
            @ApiImplicitParam(name = "insurance", value = "五险一金数额", dataType = "Double", required = false),
            @ApiImplicitParam(name = "month", value = "月份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "year", value = "年份", dataType = "Integer", required = false)
    })
    @PostMapping("/addSalary")
    public Long addSalary(@RequestBody @Validated AddSalaryVO vo){
        return salaryService.addSalary(vo);
    }

    @ApiOperation(value = "编辑工资流水")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "salarySerial", value = "工资流水号", dataType = "Long", required = true),
            @ApiImplicitParam(name = "stuffId", value = "员工id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "basicSalary", value = "基本工资", dataType = "Double", required = false),
            @ApiImplicitParam(name = "bonus", value = "奖金", dataType = "Double", required = false),
            @ApiImplicitParam(name = "totalSalary", value = "总工资", dataType = "Double", required = false),
            @ApiImplicitParam(name = "insurance", value = "五险一金数额", dataType = "Double", required = false),
            @ApiImplicitParam(name = "month", value = "月份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "year", value = "年份", dataType = "Integer", required = false)
    })
    @PostMapping("/updateSalary")
    public String updateSalary(@RequestBody @Validated(Salary.yearNotNullGroup.class) Salary salary){
        return salaryService.updateSalary(salary);
    }

    @ApiOperation(value = "删除工资流水")
    @ApiImplicitParam(name = "salarySerial", value = "工资流水号", dataType = "Long", required = true)
    @PostMapping("/deleteSalary")
    public String deleteSalary(@NotNull(message = "请指明工资流水号") Long salarySerial){
        return salaryService.deleteSalary(salarySerial);
    }

    @ApiOperation(value = "获得工资流水")
    @ApiImplicitParam(name = "salarySerial", value = "工资流水号", dataType = "Long", required = true)
    @PostMapping("/getSalary")
    public Salary getSalary(@NotNull(message = "请指明工资流水号") Long salarySerial){
        return salaryService.getSalary(salarySerial);
    }

    @ApiOperation(value = "列工资流水")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuffId", value = "员工id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "month", value = "月份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "year", value = "年份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false)
    })
    @PostMapping("/listSalary")
    public List<Salary> listSalary(@RequestBody @Validated ListSalaryVO salary){
        return salaryService.listSalary(salary);
    }

    @ApiOperation(value = "分页列工资流水")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuffId", value = "员工id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "month", value = "月份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "year", value = "年份", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面容量", dataType = "Integer", required = true)
    })
    @PostMapping("/pagedListSalary")
    public IPage<Salary> pagedListSalary(@RequestBody @Validated SalaryVO salary){
        return salaryService.pagedListSalary(salary);
    }
}
