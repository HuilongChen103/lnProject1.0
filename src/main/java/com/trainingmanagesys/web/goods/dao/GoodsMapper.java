package com.trainingmanagesys.web.goods.dao;

import com.trainingmanagesys.web.goods.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trainingmanagesys.web.goods.vo.GoodsVO;
import com.trainingmanagesys.web.goods.vo.ReturnedListGoodsVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<ReturnedListGoodsVO> listGoods(Goods goods);
}
