package com.trainingmanagesys.web.room.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.room.entity.Room;
import com.trainingmanagesys.web.room.dao.RoomMapper;
import com.trainingmanagesys.web.room.service.IRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.room.vo.RoomVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-18
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {

    private void checkRoomExistence(Long roomNum){
        if (getRoom(roomNum) == null)
            throw new APIException("该房间不存在");
    }

    @Override
    public Long addRoom(Room room) {
        baseMapper.insert(room);
        return room.getRoomNum();
    }

    @Override
    public String updateRoom(Room room) {
        checkRoomExistence(room.getRoomNum());
        String result = "更新房间失败";
        int code = baseMapper.updateById(room);
        if (code == 1)
            result = "更新房间成功";
        return result;
    }

    @Override
    public String deleteRoom(Long roomNum) {
        checkRoomExistence(roomNum);
        String result = "删除房间失败";
        int code = baseMapper.deleteById(roomNum);
        if (code == 1)
            result = "删除房间成功";
        return result;
    }

    @Override
    public Room getRoom(Long roomNum) {
        return baseMapper.selectById(roomNum);
    }

    @Override
    public List<Room> listRoom(RoomVO roomVO) {
        System.out.println("roomVO = " + roomVO);
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        if (roomVO.getRoomUsage() != null) queryWrapper.eq("room_usage", roomVO.getRoomUsage());
        if (roomVO.getAvailable() != null) queryWrapper.eq("available", roomVO.getAvailable());
//        if (roomVO.getLimit() != null) queryWrapper.last(" limit " + roomVO.getLimit());
        return baseMapper.selectList(queryWrapper);
        //return baseMapper.listRoom(roomVO.getAvailable(), roomVO.getRoomUsage());
    }

    @Override
    public IPage<Room> pagedListRoom(RoomVO roomVO) {
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        if (roomVO.getRoomUsage() != null) queryWrapper.eq("usage", roomVO.getRoomUsage());
        if (roomVO.getAvailable() != null) queryWrapper.eq("available", roomVO.getAvailable());
        if (roomVO.getLimit() != null) queryWrapper.last(" limit " + roomVO.getLimit());

        Page<Room> page = new Page<>();
        page.setCurrent(roomVO.getCurrentPage());
        page.setSize(roomVO.getPageSize());
        IPage<Room> pagedList = baseMapper.selectPage(page, queryWrapper);
        return pagedList;
    }
}
