package com.trainingmanagesys.web.goods.controller;


import com.trainingmanagesys.web.goods.entity.Goods;
import com.trainingmanagesys.web.goods.service.IGoodsService;
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

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
@Api(value = "物资", tags = {"物资接口"})
@RestController
@RequestMapping("/goods")
@Validated
public class GoodsController {

    @Autowired
    IGoodsService goodsService;

    @ApiOperation(value = "添加物资")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsCode", value = "物资编号",  dataType = "String", required = true),
            @ApiImplicitParam(name = "name", value = "物资名称",  dataType = "String", required = true),
            @ApiImplicitParam(name = "catagory", value = "类目",  dataType = "String", required = true),
            @ApiImplicitParam(name = "picId", value = "采办人id(person in charge)",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "stockInDate", value = "入库时间",  dataType = "Date", required = false),
            @ApiImplicitParam(name = "stockOutDate", value = "出库时间",  dataType = "Date", required = false),
            @ApiImplicitParam(name = "price", value = "价格",  dataType = "BigDecimal", required = false),
            @ApiImplicitParam(name = "roomNum", value = "现在的地点（房间号）",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "comment", value = "备注",  dataType = "String", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addGoods")
    public String addGoods(@RequestBody @Validated Goods goods){
        return goodsService.addGoods(goods);
    }

    @ApiOperation(value = "更新物资")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsCode", value = "物资编号",  dataType = "String", required = true),
            @ApiImplicitParam(name = "name", value = "物资名称",  dataType = "String", required = true),
            @ApiImplicitParam(name = "catagory", value = "类目",  dataType = "String", required = true),
            @ApiImplicitParam(name = "picId", value = "采办人id(person in charge)",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "stockInDate", value = "入库时间",  dataType = "Date", required = false),
            @ApiImplicitParam(name = "stockOutDate", value = "出库时间",  dataType = "Date", required = false),
            @ApiImplicitParam(name = "price", value = "价格",  dataType = "BigDecimal", required = false),
            @ApiImplicitParam(name = "roomNum", value = "现在的地点（房间号）",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "comment", value = "备注",  dataType = "String", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/updateGoods")
    public String updateGoods(@RequestBody @Validated Goods goods){
        return goodsService.updateGoods(goods);
    }

    @ApiOperation(value = "删除物资")
    @ApiImplicitParam(name = "goodsCode", value = "物资编号",  dataType = "String", required = true)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/deleteGoods")
    public String deleteGoods(@RequestBody @Validated String goodsCode){
        return goodsService.deleteGoods(goodsCode);
    }

    @ApiOperation(value = "获取物资")
    @ApiImplicitParam(name = "goodsCode", value = "物资编号",  dataType = "String", required = true)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/getGoods")
    public Goods getGoods(@RequestBody @Validated String goodsCode){
        return goodsService.getGoods(goodsCode);
    }
}
