package com.trainingmanagesys.web.recruit.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.recruit.entity.Recruitee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.recruit.vo.RecruiteeVO;

import java.util.List;

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

    List<Recruitee> listRecruitee(RecruiteeVO vo);

    IPage<Recruitee> pagedListRecruitee(RecruiteeVO vo);
}
