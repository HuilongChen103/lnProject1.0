package com.trainingmanagesys.web.recruit.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.recruit.entity.Recruit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.recruit.vo.RecruitVO;

import java.util.List;

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

    List<Recruit> listRecruit(RecruitVO vo);

    IPage<Recruit> pagedListRecruit(RecruitVO vo);
}
