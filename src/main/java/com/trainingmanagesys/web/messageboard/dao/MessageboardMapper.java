package com.trainingmanagesys.web.messageboard.dao;

import com.trainingmanagesys.web.messageboard.entity.Messageboard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trainingmanagesys.web.messageboard.vo.ReturnedListMessageboardVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
public interface MessageboardMapper extends BaseMapper<Messageboard> {

    List<ReturnedListMessageboardVO> listMessageWithName(String classCode);
}
