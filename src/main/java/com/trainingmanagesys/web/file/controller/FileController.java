package com.trainingmanagesys.web.file.controller;


import com.trainingmanagesys.web.file.entity.File;
import com.trainingmanagesys.web.file.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoying
 * @since 2020-05-13
 */
@Api(value = "文件", tags = {"文件操作接口"})
@RestController
@RequestMapping("/file")
@Validated
public class FileController {

    @Autowired
    IFileService fileService;

    @ApiOperation(value = "添加文件接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "文件名", dataType = "String", required = false),
            @ApiImplicitParam(name = "displayname", value = "文件展示名", dataType = "String", required = false),
            @ApiImplicitParam(name = "extension", value = "文件扩展名", dataType = "String", required = false),
            @ApiImplicitParam(name = "contenttype", value = "文件种类", dataType = "String", required = false),
            @ApiImplicitParam(name = "fileData", value = "文件二进制格式", dataType = "byte[]", required = false),
            @ApiImplicitParam(name = "fileSize", value = "文件大小", dataType = "String", required = false),
            @ApiImplicitParam(name = "uploadTime", value = "上传时间", dataType = "Date", required = false),
            @ApiImplicitParam(name = "uploaderId", value = "上传人id", dataType = "Long", required = false)
    })
    @PostMapping("/addFile")
    public String addFile(@RequestBody @Validated File file){
        return fileService.addFile(file);
    }

    @ApiOperation(value = "更新文件接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileSerial", value = "文件流水号", dataType = "Long", required = true),
            @ApiImplicitParam(name = "name", value = "文件名", dataType = "String", required = false),
            @ApiImplicitParam(name = "displayname", value = "文件展示名", dataType = "String", required = false),
            @ApiImplicitParam(name = "extension", value = "文件扩展名", dataType = "String", required = false),
            @ApiImplicitParam(name = "contenttype", value = "文件种类", dataType = "String", required = false),
            @ApiImplicitParam(name = "fileData", value = "文件二进制格式", dataType = "byte[]", required = false),
            @ApiImplicitParam(name = "fileSize", value = "文件大小", dataType = "String", required = false),
            @ApiImplicitParam(name = "uploadTime", value = "上传时间", dataType = "Date", required = false),
            @ApiImplicitParam(name = "uploaderId", value = "上传人id", dataType = "Long", required = false)
    })
    @PostMapping("/updateFile")
    public String updateFile(@RequestBody @Validated File file){
        return fileService.updateFile(file);
    }

    @ApiOperation(value = "获取文件")
    @ApiImplicitParam(name = "fileSerial", value = "文件流水号", dataType = "Long", required = true)
    @GetMapping("/getFile")
    public File getFile(@NotNull(message = "请指明文件流水号") Long fileSerial){
        return fileService.getFile(fileSerial);
    }
}
