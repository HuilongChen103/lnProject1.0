package com.trainingmanagesys.web.room.dao;

import com.trainingmanagesys.web.room.entity.Room;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trainingmanagesys.web.room.vo.RoomVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoying
 * @since 2020-05-18
 */
public interface RoomMapper extends BaseMapper<Room> {

    List<Room> listRoom(@Param("available") Integer available, @Param("usage") String usage);
}
