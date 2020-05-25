package com.trainingmanagesys.web.recruit.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.recruit.entity.Recruit;
import com.trainingmanagesys.web.recruit.service.IRecruitService;
import com.trainingmanagesys.web.recruit.vo.RecruitVO;
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
@Api(value = "招聘", tags = {"招聘操作接口"})
@RestController
@RequestMapping("/recruit")
@Validated
public class RecruitController {

    @Autowired
    IRecruitService recruitService;

    @ApiOperation(value = "添加招聘", notes = "添加招聘")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recruitCode", value = "招聘编号", dataType = "String", required = true),
            @ApiImplicitParam(name = "picId", value = "主办人id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "Date", required = false),
            @ApiImplicitParam(name = "place", value = "地点", dataType = "Date", required = false),
            @ApiImplicitParam(name = "method", value = "方式（网络，实地等）", dataType = "String", required = false),
            @ApiImplicitParam(name = "catagory", value = "招聘对象类型（教师，学生，职工）", dataType = "String", required = false),
            @ApiImplicitParam(name = "comment", value = "备注", dataType = "String", required = false),
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addRecruit")
    public String addRecruit(@RequestBody @Validated(Recruit.addKeyGroup.class) Recruit recruit){
        return recruitService.addRecruit(recruit);
    }

    @ApiOperation(value = "更新招聘", notes = "更新招聘")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recruitCode", value = "招聘编号", dataType = "String", required = true),
            @ApiImplicitParam(name = "picId", value = "主办人id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "Date", required = false),
            @ApiImplicitParam(name = "place", value = "地点", dataType = "Date", required = false),
            @ApiImplicitParam(name = "method", value = "方式（网络，实地等）", dataType = "String", required = false),
            @ApiImplicitParam(name = "catagory", value = "招聘对象类型（教师，学生，职工）", dataType = "String", required = false),
            @ApiImplicitParam(name = "comment", value = "备注", dataType = "String", required = false),
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/updateRecruit")
    public String updateRecruit(@RequestBody @Validated Recruit recruit){
        return recruitService.updateRecruit(recruit);
    }

    @ApiOperation(value = "删除招聘", notes = "删除招聘")
    @ApiImplicitParam(name = "recruitCode", value = "招聘编号", dataType = "String", required = true)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/deleteRecruit")
    public String deleteRecruit(@NotNull(message = "请输入招聘编号") String recruitCode){
        return recruitService.deleteRecruit(recruitCode);
    }

    @ApiOperation(value = "获取招聘", notes = "获取招聘")
    @ApiImplicitParam(name = "recruitCode", value = "招聘编号", dataType = "String", required = true)
    @PostMapping("/getRecruit")
    public Recruit getRecruit(@NotNull(message = "请输入招聘编号") String recruitCode){
        return recruitService.getRecruit(recruitCode);
    }

    @ApiOperation(value = "列招聘", notes = "列招聘")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "picId", value = "主办人id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "Date", required = false),
            @ApiImplicitParam(name = "place", value = "模糊查找地点", dataType = "Date", required = false),
            @ApiImplicitParam(name = "method", value = "方式（网络，实地等）", dataType = "String", required = false),
            @ApiImplicitParam(name = "catagory", value = "招聘对象类型（教师，学生，职工）", dataType = "String", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false)
    })
    @PostMapping("/listRecruit")
    public List<Recruit> listRecruit(@RequestBody RecruitVO vo){
        return recruitService.listRecruit(vo);
    }

    @ApiOperation(value = "分页列招聘", notes = "分页列招聘")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "picId", value = "主办人id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "date", value = "日期", dataType = "Date", required = false),
            @ApiImplicitParam(name = "place", value = "模糊地点", dataType = "Date", required = false),
            @ApiImplicitParam(name = "method", value = "方式（网络，实地等）", dataType = "String", required = false),
            @ApiImplicitParam(name = "catagory", value = "招聘对象类型（教师，学生，职工）", dataType = "String", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面容量", dataType = "Integer", required = true)
    })
    @PostMapping("/pagedListRecruit")
    public IPage<Recruit> pagedListRecruit(@RequestBody @Validated(RecruitVO.listKeyGroup.class) RecruitVO vo){
        return recruitService.pagedListRecruit(vo);
    }
}
