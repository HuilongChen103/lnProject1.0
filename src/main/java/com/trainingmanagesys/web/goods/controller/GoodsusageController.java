package com.trainingmanagesys.web.goods.controller;


import com.trainingmanagesys.web.goods.entity.Goodsusage;
import com.trainingmanagesys.web.goods.service.IGoodsusageService;
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

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
@Api(value = "物资使用", tags = {"物资使用接口"})
@RestController
@RequestMapping("/goods")
@Validated
public class GoodsusageController {

    @Autowired
    IGoodsusageService goodsusageService;

    @ApiOperation(value = "添加物资使用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usageCode", value = "使用编号",  dataType = "String", required = true),
            @ApiImplicitParam(name = "goodsCode", value = "器材编号",  dataType = "String", required = true),
            @ApiImplicitParam(name = "rentorId", value = "租借者id（若为空，则是官方分配，比如教室的桌椅）",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "picId", value = "负责人id（person in charge）",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "rentDate", value = "租借时间",  dataType = "Date", required = false),
            @ApiImplicitParam(name = "stockOutDate", value = "出库时间",  dataType = "Date", required = false),
            @ApiImplicitParam(name = "returnDate", value = "归还时间",  dataType = "Date", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addGoodsusage")
    public String addGoodsusage(@RequestBody @Validated({Goodsusage.addKeyGroup.class, Goodsusage.addAdditionGroup.class}) Goodsusage goodsusage){
        return goodsusageService.addGoodsusage(goodsusage);
    }

    @ApiOperation(value = "更新物资使用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usageCode", value = "使用编号",  dataType = "String", required = true),
            @ApiImplicitParam(name = "goodsCode", value = "器材编号",  dataType = "String", required = false),
            @ApiImplicitParam(name = "rentorId", value = "租借者id（若为空，则是官方分配，比如教室的桌椅）",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "picId", value = "负责人id（person in charge）",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "rentDate", value = "租借时间",  dataType = "Date", required = false),
            @ApiImplicitParam(name = "stockOutDate", value = "出库时间",  dataType = "Date", required = false),
            @ApiImplicitParam(name = "returnDate", value = "归还时间",  dataType = "Date", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/updateGoodsusage")
    public String updateGoodsusage(@RequestBody @Validated Goodsusage goodsusage){
        return goodsusageService.addGoodsusage(goodsusage);
    }

    @ApiOperation(value = "删除物资使用")
    @ApiImplicitParam(name = "usageCode", value = "使用编号",  dataType = "String", required = true)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/deleteGoodsusage")
    public String deleteGoodsusage(@NotNull(message = "请指明物资使用编号") String usageCode){
        return goodsusageService.deleteGoodsusage(usageCode);
    }

    @ApiOperation(value = "获取物资使用")
    @ApiImplicitParam(name = "usageCode", value = "使用编号",  dataType = "String", required = true)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/getGoodsusage")
    public Goodsusage getGoodsusage(@NotNull(message = "请指明物资使用编号") String usageCode){
        return goodsusageService.getGoodsusage(usageCode);
    }
}
