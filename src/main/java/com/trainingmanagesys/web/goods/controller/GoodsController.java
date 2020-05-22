package com.trainingmanagesys.web.goods.controller;


import com.trainingmanagesys.web.goods.entity.Goods;
import com.trainingmanagesys.web.goods.service.IGoodsService;
import com.trainingmanagesys.web.goods.vo.GoodsVO;
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

import java.util.List;

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
    public String addGoods(@RequestBody @Validated({Goods.addKeyGroup.class, Goods.addAdditionGroup.class}) Goods goods){
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
    @PostMapping("/getGoods")
    public Goods getGoods(@RequestBody @Validated String goodsCode){
        return goodsService.getGoods(goodsCode);
    }

    @ApiOperation(value = "列物资")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "物资名称",  dataType = "String", required = false),
            @ApiImplicitParam(name = "catagory", value = "类目",  dataType = "String", required = false),
            @ApiImplicitParam(name = "picId", value = "采办人id(person in charge)",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "stockInDate", value = "入库时间",  dataType = "Date", required = false),
            @ApiImplicitParam(name = "stockOutDate", value = "出库时间",  dataType = "Date", required = false),
            @ApiImplicitParam(name = "priceMax", value = "最大价格",  dataType = "Double", required = false),
            @ApiImplicitParam(name = "priceMin", value = "最低价格",  dataType = "Double", required = false),
            @ApiImplicitParam(name = "roomNum", value = "现在的地点（房间号）",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "limit", value = "数量",  dataType = "Integer", required = false)
    })
    @PostMapping("/listGoods")
    public List<Goods> listGoods(@RequestBody @Validated GoodsVO goods){
        return goodsService.listGoods(goods);
    }

    @ApiOperation(value = "分页列物资")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "物资名称",  dataType = "String", required = false),
            @ApiImplicitParam(name = "catagory", value = "类目",  dataType = "String", required = false),
            @ApiImplicitParam(name = "picId", value = "采办人id(person in charge)",  dataType = "Long", required = false),
            @ApiImplicitParam(name = "stockInDate", value = "入库时间",  dataType = "Date", required = false),
            @ApiImplicitParam(name = "stockOutDate", value = "出库时间",  dataType = "Date", required = false),
            @ApiImplicitParam(name = "priceMax", value = "最大价格",  dataType = "Double", required = false),
            @ApiImplicitParam(name = "priceMin", value = "最低价格",  dataType = "Double", required = false),
            @ApiImplicitParam(name = "roomNum", value = "现在的地点（房间号）",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "limit", value = "数量",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面",  dataType = "Integer", required = false),
            @ApiImplicitParam(name = "pageSize", value = "页面容量",  dataType = "Integer", required = false)
    })
    @PostMapping("/pagedListGoods")
    public List<Goods> pagedListGoods(@RequestBody @Validated GoodsVO goods){
        return goodsService.listGoods(goods);
    }
}
