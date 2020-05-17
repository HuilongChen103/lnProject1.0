package com.trainingmanagesys.web.finance.dao;

import com.trainingmanagesys.web.finance.entity.Finance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoying
 * @since 2020-05-12
 */
public interface FinanceMapper extends BaseMapper<Finance> {

    Integer insertAll(Finance finance);
}
