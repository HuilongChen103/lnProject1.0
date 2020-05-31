package com.trainingmanagesys.web.stuff.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.stuff.entity.Stuff;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.stuff.vo.ReturnedSpecialListStuffVO;
import com.trainingmanagesys.web.stuff.vo.StuffVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-21
 */
public interface IStuffService extends IService<Stuff> {

    Long addStuff(Stuff stuff);

    String updateStuff(Stuff stuff);

    String deleteStuff(Long stuffId);

    Stuff getStuff(Long stuffId);

    List<Stuff> listStuff(StuffVO vo);

    IPage<Stuff> pagedListStuff(StuffVO vo);

    List<ReturnedSpecialListStuffVO> listSpecializedStuff(Stuff stuff);
}
