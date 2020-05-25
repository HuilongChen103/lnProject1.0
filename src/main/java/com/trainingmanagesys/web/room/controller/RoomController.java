package com.trainingmanagesys.web.room.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.room.entity.Room;
import com.trainingmanagesys.web.room.service.IRoomService;
import com.trainingmanagesys.web.room.vo.RoomVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoying
 * @since 2020-05-18
 */
@Api(value = "房间", tags = {"房间操作接口"})
@RestController
@RequestMapping("/room")
@Validated
public class RoomController {

    @Autowired
    IRoomService roomService;

    @ApiOperation(value = "添加房间", notes = "添加房间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usage", value = "房间用途", dataType = "String", required = false),
            @ApiImplicitParam(name = "available", value = "是否在使用", dataType = "Integer", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addRoom")
    public Long addRoom(@RequestBody Room room){
        return roomService.addRoom(room);
    }

    @ApiOperation(value = "更新房间", notes = "更新房间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomNum", value = "房间号码", dataType = "Long", required = true),
            @ApiImplicitParam(name = "usage", value = "房间用途", dataType = "String", required = false),
            @ApiImplicitParam(name = "available", value = "是否在使用", dataType = "Integer", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/updateRoom")
    public String updateRoom(@RequestBody @Validated Room room){
        return roomService.updateRoom(room);
    }

    @ApiOperation(value = "删除房间", notes = "删除房间")
    @ApiImplicitParam(name = "roomNum", value = "房间号码", dataType = "Long", required = true)
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/deleteRoom")
    public String deleteRoom(@NotNull(message = "请指明房间号") Long roomNum){
        return roomService.deleteRoom(roomNum);
    }

    @ApiOperation(value = "列房间", notes = "列房间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usage", value = "房间用途", dataType = "String", required = false),
            @ApiImplicitParam(name = "available", value = "是否在使用", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false)
    })
    @GetMapping("/listRoom")
    public List<Room> listRoom(@RequestBody @Validated RoomVO roomVO){
        return roomService.listRoom(roomVO);
    }

    @ApiOperation(value = "分页列房间", notes = "分页列房间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usage", value = "房间用途", dataType = "String", required = false),
            @ApiImplicitParam(name = "available", value = "是否在使用", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面容量", dataType = "Integer", required = true)
    })
    @GetMapping("/pagedListRoom")
    public IPage<Room> pagedListRoom(@RequestBody @Validated({RoomVO.listKeyGroup.class}) RoomVO roomVO){
        return roomService.pagedListRoom(roomVO);
    }

}
