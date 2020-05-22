package com.trainingmanagesys.web.recruit.service;

import com.trainingmanagesys.web.recruit.entity.Recruitee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-22
 */
public interface IRecruiteeService extends IService<Recruitee> {

    String addRecruitee(Recruitee recruitee);

    String updateRecruitee(Recruitee recruitee);

    String deleteRecruitee(String recruiteeCode);

    Recruitee getRecruitee(String recruiteeCode);
}
