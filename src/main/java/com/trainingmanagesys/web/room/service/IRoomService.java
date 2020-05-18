package com.trainingmanagesys.web.room.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.room.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.room.vo.RoomVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-18
 */
public interface IRoomService extends IService<Room> {

    Long addRoom(Room room);

    String updateRoom(Room room);

    String deleteRoom(Long roomNum);

    List<Room> listRoom(RoomVO roomVO);

    IPage<Room> pagedListRoom(RoomVO roomVO);
}
