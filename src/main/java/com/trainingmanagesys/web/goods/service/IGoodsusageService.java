package com.trainingmanagesys.web.goods.service;

import com.trainingmanagesys.web.goods.entity.Goodsusage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
public interface IGoodsusageService extends IService<Goodsusage> {

    String addGoodsusage(Goodsusage goodsusage);

    String updateGoodsusage(Goodsusage goodsusage);

    String deleteGoodsusage(String usageCode);

    Goodsusage getGoodsusage(String usageCode);
}
