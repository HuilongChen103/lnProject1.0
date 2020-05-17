package com.trainingmanagesys.web.user.dao;

import com.trainingmanagesys.web.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoying
 * @since 2020-04-29
 */
public interface UserMapper extends BaseMapper<User> {
    String getPosition(@Param("uid") Long uid);
}
