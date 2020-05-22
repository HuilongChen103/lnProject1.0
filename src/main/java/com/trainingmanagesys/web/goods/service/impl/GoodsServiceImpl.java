package com.trainingmanagesys.web.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.web.goods.entity.Goods;
import com.trainingmanagesys.web.goods.dao.GoodsMapper;
import com.trainingmanagesys.web.goods.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.goods.vo.GoodsVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Override
    public String addGoods(Goods goods) {
        String result = "该物资编号已经存在";
        if (baseMapper.selectById(goods.getGoodsCode()) == null)
            return result;
        baseMapper.insert(goods);
        return goods.getGoodsCode();
    }

    @Override
    public String updateGoods(Goods goods) {
        String result = "更新物资失败";
        int code = baseMapper.updateById(goods);
        if (code == 1)
            result = "更新物资成功";
        return result;
    }

    @Override
    public String deleteGoods(String goodsCode) {
        String result = "删除物资失败";
        int code = baseMapper.deleteById(goodsCode);
        if (code == 1)
            result = "删除物资成功";
        return result;
    }

    @Override
    public Goods getGoods(String goodsCode) {
        return baseMapper.selectById(goodsCode);
    }

    @Override
    public List<Goods> listGoods(GoodsVO vo) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if (vo.getPicId() != null) queryWrapper.eq("PIC_id", vo.getPicId());
        if (vo.getName() != null) queryWrapper.eq("name", vo.getName());
        if (vo.getCatagory() != null) queryWrapper.eq("catagory", vo.getCatagory());
        if (vo.getStockInDate() != null) queryWrapper.eq("stock_in_date", vo.getStockInDate());
        if (vo.getStockOutDate() != null) queryWrapper.eq("stock_out_date", vo.getStockInDate());
        if (vo.getPriceMax() != null) queryWrapper.le("price", vo.getPriceMax());
        if (vo.getPriceMin() != null) queryWrapper.ge("price", vo.getPriceMin());
        if (vo.getRoomNum() != null) queryWrapper.eq("room_num", vo.getRoomNum());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Goods> pagedListGoods(GoodsVO vo) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if (vo.getPicId() != null) queryWrapper.eq("PIC_id", vo.getPicId());
        if (vo.getName() != null) queryWrapper.eq("name", vo.getName());
        if (vo.getCatagory() != null) queryWrapper.eq("catagory", vo.getCatagory());
        if (vo.getStockInDate() != null) queryWrapper.eq("stock_in_date", vo.getStockInDate());
        if (vo.getStockOutDate() != null) queryWrapper.eq("stock_out_date", vo.getStockInDate());
        if (vo.getPriceMax() != null) queryWrapper.le("price", vo.getPriceMax());
        if (vo.getPriceMin() != null) queryWrapper.ge("price", vo.getPriceMin());
        if (vo.getRoomNum() != null) queryWrapper.eq("room_num", vo.getRoomNum());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());

        Page<Goods> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getPageSize());
        IPage<Goods> pagedList = baseMapper.selectPage(page, queryWrapper);
        return pagedList;
    }
}
