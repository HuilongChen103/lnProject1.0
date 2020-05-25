package com.trainingmanagesys.web.recruit.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.recruit.entity.Recruitee;
import com.trainingmanagesys.web.recruit.service.IRecruiteeService;
import com.trainingmanagesys.web.recruit.vo.RecruiteeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
 * @since 2020-05-22
 */
@Api(value = "受招募人", tags = {"受招募人操作接口"})
@RestController
@RequestMapping("/recruitee")
@Validated
public class RecruiteeController {

    @Autowired
    IRecruiteeService recruiteeService;

    @ApiOperation(value = "添加受招募人", notes = "添加受招募人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recruiteeCode", value = "受招募人流水号", dataType = "String", required = true),
            @ApiImplicitParam(name = "recruitCode", value = "招聘会编号", dataType = "String", required = true),
            @ApiImplicitParam(name = "name", value = "名字", dataType = "String", required = false),
            @ApiImplicitParam(name = "resumeFile", value = "简历文档", dataType = "Long", required = false),
            @ApiImplicitParam(name = "catagory", value = "类型（学生、职员、教师）", dataType = "String", required = false),
            @ApiImplicitParam(name = "auditSerial", value = "审核编号", dataType = "Long", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addRecruitee")
    public String addRecruitee(@RequestBody @Validated({Recruitee.addKeyGroup.class, Recruitee.addAdditionGroup.class}) Recruitee recruitee){
        return recruiteeService.addRecruitee(recruitee);
    }

    @ApiOperation(value = "更新受招募人", notes = "更新受招募人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recruiteeCode", value = "受招募人流水号", dataType = "String", required = true),
            @ApiImplicitParam(name = "recruitCode", value = "招聘会编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "name", value = "名字", dataType = "String", required = false),
            @ApiImplicitParam(name = "resumeFile", value = "简历文档", dataType = "Long", required = false),
            @ApiImplicitParam(name = "catagory", value = "类型（学生、职员、教师）", dataType = "String", required = false),
            @ApiImplicitParam(name = "auditSerial", value = "审核编号", dataType = "Long", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/updateRecruitee")
    public String updateRecruitee(@RequestBody @Validated Recruitee recruitee){
        return recruiteeService.updateRecruitee(recruitee);
    }

    @ApiOperation(value = "删除受招募人", notes = "删除受招募人")
    @ApiImplicitParam(name = "recruiteeCode", value = "受招募人流水号", dataType = "String", required = true)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/deleteRecruitee")
    public String deleteRecruitee(@NotNull(message = "请指明受招募人流水号") String recruiteeCode){
        return recruiteeService.deleteRecruitee(recruiteeCode);
    }

    @ApiOperation(value = "获取受招募人", notes = "获取受招募人")
    @ApiImplicitParam(name = "recruiteeCode", value = "受招募人流水号", dataType = "String", required = true)
    @PostMapping("/getRecruitee")
    public Recruitee getRecruitee(@NotNull(message = "请指明受招募人流水号") String recruiteeCode){
        return recruiteeService.getRecruitee(recruiteeCode);
    }

    @ApiOperation(value = "列受招募人", notes = "列受招募人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recruitCode", value = "招聘会编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "catagory", value = "类型（学生、职员、教师）", dataType = "String", required = false),
            @ApiImplicitParam(name = "auditSerial", value = "审核编号", dataType = "Long", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false)
    })
    @PostMapping("/listRecruitee")
    public List<Recruitee> listRecruitee(@RequestBody RecruiteeVO vo){
        return recruiteeService.listRecruitee(vo);
    }

    @ApiOperation(value = "分页列受招募人", notes = "分页列受招募人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recruitCode", value = "招聘会编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "catagory", value = "类型（学生、职员、教师）", dataType = "String", required = false),
            @ApiImplicitParam(name = "auditSerial", value = "审核编号", dataType = "Long", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面容量", dataType = "Integer", required = true)
    })
    @PostMapping("/pagedListRecruitee")
    public IPage<Recruitee> pagedListRecruitee(@RequestBody @Validated(RecruiteeVO.listKeyGroup.class) RecruiteeVO vo){
        return recruiteeService.pagedListRecruitee(vo);
    }
}
