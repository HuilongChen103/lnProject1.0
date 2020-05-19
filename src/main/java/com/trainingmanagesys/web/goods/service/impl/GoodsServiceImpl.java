package com.trainingmanagesys.web.goods.service.impl;

import com.trainingmanagesys.web.goods.entity.Goods;
import com.trainingmanagesys.web.goods.dao.GoodsMapper;
import com.trainingmanagesys.web.goods.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
