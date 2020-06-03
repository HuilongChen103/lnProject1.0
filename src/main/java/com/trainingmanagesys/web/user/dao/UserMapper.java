package com.trainingmanagesys.web.user.dao;

import com.trainingmanagesys.web.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trainingmanagesys.web.user.vo.ReturnedListUserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    Long insertUserAndGetId(User user);

    List<ReturnedListUserVO> listUser(User user);
}
