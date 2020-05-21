package com.trainingmanagesys.web.stuff.service;

import com.trainingmanagesys.web.stuff.entity.Stuff;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
