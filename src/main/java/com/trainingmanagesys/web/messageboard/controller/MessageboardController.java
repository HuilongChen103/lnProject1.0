package com.trainingmanagesys.web.messageboard.controller;


import com.trainingmanagesys.web.messageboard.entity.Messageboard;
import com.trainingmanagesys.web.messageboard.service.IMessageboardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
@Api(value = "留言", tags = {"留言操作接口"})
@RestController
@RequestMapping("/message")
@Validated
public class MessageboardController {

    @Autowired
    IMessageboardService messageboardService;

    @ApiOperation(value = "添加留言")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uploaderId", value = "上传人id", dataType = "Long", required = true),
            @ApiImplicitParam(name = "content", value = "内容", dataType = "String", required = false),
            @ApiImplicitParam(name = "date", value = "时间", dataType = "Date", required = false),
            @ApiImplicitParam(name = "classCode", value = "班级号", dataType = "String", required = false),
            @ApiImplicitParam(name = "reply", value = "回复", dataType = "Long", required = false)
    })
    @PostMapping("/addMessage")
    public Long addMessage(@RequestBody @Validated Messageboard messageboard){
        return messageboardService.addMessage(messageboard);
    }

    @ApiOperation(value = "更新留言")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "messageSerial", value = "留言流水号", dataType = "Long", required = true),
            @ApiImplicitParam(name = "uploaderId", value = "上传人id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "content", value = "内容", dataType = "String", required = false),
            @ApiImplicitParam(name = "date", value = "时间", dataType = "Date", required = false),
            @ApiImplicitParam(name = "classCode", value = "班级号", dataType = "String", required = false),
            @ApiImplicitParam(name = "reply", value = "回复", dataType = "Long", required = false)
    })
    @PostMapping("/updateMessage")
    public String updateMessage(@RequestBody @Validated Messageboard messageboard){
        return messageboardService.updateMessage(messageboard);
    }

    @ApiOperation(value = "删除留言")
    @ApiImplicitParam(name = "messageSerial", value = "留言流水号", dataType = "Long", required = true)
    @PostMapping("/deleteMessage")
    public String deleteMessage(@NotNull(message = "请指明留言流水号") Long messageSerial){
        return messageboardService.deleteMessage(messageSerial);
    }

    @ApiOperation(value = "获得留言")
    @ApiImplicitParam(name = "messageSerial", value = "留言流水号", dataType = "Long", required = true)
    @PostMapping("/getMessage")
    public Messageboard getMessage(@NotNull(message = "请指明留言流水号") Long messageSerial){
        return messageboardService.getMessage(messageSerial);
    }

    @ApiOperation(value = "列所有留言")
    @PostMapping("/listMessage")
    public List<Messageboard> listMessage(){
        return messageboardService.listMessage();
    }
}
