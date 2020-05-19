package com.trainingmanagesys.web.goods.service.impl;

import com.trainingmanagesys.web.goods.entity.Goodsusage;
import com.trainingmanagesys.web.goods.dao.GoodsusageMapper;
import com.trainingmanagesys.web.goods.service.IGoodsusageService;
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
public class GoodsusageServiceImpl extends ServiceImpl<GoodsusageMapper, Goodsusage> implements IGoodsusageService {

    @Override
    public String addGoodsusage(Goodsusage goodsusage) {
        String result = "该用户已经存在";
        if (baseMapper.selectById(goodsusage.getUsageCode()) != null)
            return result;
        baseMapper.insert(goodsusage);
        return goodsusage.getUsageCode();
    }

    @Override
    public String updateGoodsusage(Goodsusage goodsusage) {
        String result = "更新物资使用失败";
        int code = baseMapper.updateById(goodsusage);
        if (code == 1)
            result = "更新物资使用成功";
        return result;
    }

    @Override
    public String deleteGoodsusage(String usageCode) {
        String result = "删除物资使用失败";
        int code = baseMapper.deleteById(usageCode);
        if (code == 1)
            result = "删除物资使用成功";
        return result;
    }

    @Override
    public Goodsusage getGoodsusage(String usageCode) {
        return baseMapper.selectById(usageCode);
    }
}
