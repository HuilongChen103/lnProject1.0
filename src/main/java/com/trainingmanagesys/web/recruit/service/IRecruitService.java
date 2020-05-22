package com.trainingmanagesys.web.recruit.service;

import com.trainingmanagesys.web.recruit.entity.Recruit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-22
 */
public interface IRecruitService extends IService<Recruit> {

    String addRecruit(Recruit recruit);

    String updateRecruit(Recruit recruit);

    String deleteRecruit(String recruitCode);

    Recruit getRecruit(String recruitCode);
}
