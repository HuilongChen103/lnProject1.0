package com.trainingmanagesys.web.recruit.dao;

import com.trainingmanagesys.web.recruit.entity.Recruit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoying
 * @since 2020-05-22
 */
public interface RecruitMapper extends BaseMapper<Recruit> {

    Recruit getRecruit(@Param("recruitCode") String recruitCode, @Param("enable") Integer enable);

    Recruit getRecruitWithPICId(@Param("picId") Long picId, @Param("enable") Integer enable);
}
