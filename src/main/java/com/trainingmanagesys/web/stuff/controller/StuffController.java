package com.trainingmanagesys.web.stuff.controller;


import com.trainingmanagesys.web.stuff.entity.Stuff;
import com.trainingmanagesys.web.stuff.service.IStuffService;
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

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoying
 * @since 2020-05-21
 */
@Api(value = "职工", tags = {"职工操作接口"})
@RestController
@RequestMapping("/stuff")
@Validated
public class StuffController {

    @Autowired
    IStuffService stuffService;

    @ApiOperation(value = "添加职工")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuffId", value = "职工id", dataType = "Long", required = true),
            @ApiImplicitParam(name = "departmentId", value = "部门id", dataType = "Long", required = true),
            @ApiImplicitParam(name = "position", value = "职位", dataType = "String", required = false)
    })
    @PostMapping("/addStuff")
    public Long addStuff(@RequestBody @Validated({Stuff.addKeyGroup.class, Stuff.addAdditionGroup.class}) Stuff stuff){
        return stuffService.addStuff(stuff);
    }

    @ApiOperation(value = "更新职工")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuffId", value = "职工id", dataType = "Long", required = true),
            @ApiImplicitParam(name = "departmentId", value = "部门id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "position", value = "职位", dataType = "String", required = false)
    })
    @PostMapping("/updateStuff")
    public String updateStuff(@RequestBody @Validated({Stuff.updateGroup.class, Stuff.updateGroup.class}) Stuff stuff){
        return stuffService.updateStuff(stuff);
    }

    @ApiOperation(value = "删除职工")
    @ApiImplicitParam(name = "stuffId", value = "职工id", dataType = "Long", required = true)
    @PostMapping("/deleteStuff")
    public String deleteStuff(@NotNull(message = "请指明职工id") Long stuffId){
        return stuffService.deleteStuff(stuffId);
    }

    @ApiOperation(value = "获取职工")
    @ApiImplicitParam(name = "stuffId", value = "职工id", dataType = "Long", required = true)
    @PostMapping("/getStuff")
    public Stuff getStuff(@NotNull(message = "请指明职工id") Long stuffId){
        return stuffService.getStuff(stuffId);
    }
}
