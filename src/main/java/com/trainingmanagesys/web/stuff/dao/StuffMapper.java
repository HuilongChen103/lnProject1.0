package com.trainingmanagesys.web.stuff.dao;

import com.trainingmanagesys.web.stuff.entity.Stuff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trainingmanagesys.web.stuff.vo.ReturnedSpecialListStuffVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoying
 * @since 2020-05-21
 */
public interface StuffMapper extends BaseMapper<Stuff> {

    List<ReturnedSpecialListStuffVO> listSpecializedStuff(Stuff stuff);
}
