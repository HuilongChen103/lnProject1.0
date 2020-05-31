package com.trainingmanagesys.web.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.goods.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.goods.vo.GoodsVO;
import com.trainingmanagesys.web.goods.vo.ReturnedListGoodsVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
public interface IGoodsService extends IService<Goods> {

    String addGoods(Goods goods);

    String updateGoods(Goods goods);

    String deleteGoods(String goodsCode);

    Goods getGoods(String goodsCode);

    List<ReturnedListGoodsVO> listGoods(Goods vo);

    IPage<Goods> pagedListGoods(GoodsVO vo);
}
