package com.trainingmanagesys.web.goods.service.impl;

import com.trainingmanagesys.conf.exception.APIException;
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

    private void checkGoodsUsageExistence(String usageCode){
        Goodsusage temp = getGoodsusage(usageCode);
        if (temp == null)
            throw new APIException("该物资管理不存在");
    }

    @Override
    public String addGoodsusage(Goodsusage goodsusage) {
        checkGoodsUsageExistence(goodsusage.getUsageCode());
        baseMapper.insert(goodsusage);
        return goodsusage.getUsageCode();
    }

    @Override
    public String updateGoodsusage(Goodsusage goodsusage) {
        checkGoodsUsageExistence(goodsusage.getUsageCode());
        String result = "更新物资使用失败";
        int code = baseMapper.updateById(goodsusage);
        if (code == 1)
            result = "更新物资使用成功";
        return result;
    }

    @Override
    public String deleteGoodsusage(String usageCode) {
        getGoodsusage(usageCode);
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
