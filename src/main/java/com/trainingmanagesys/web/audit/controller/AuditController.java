package com.trainingmanagesys.web.audit.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.audit.entity.Audit;
import com.trainingmanagesys.web.audit.service.IAuditService;
import com.trainingmanagesys.web.audit.vo.AuditVO;
import com.trainingmanagesys.web.audit.vo.ReturnAuditVO;
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
 * @since 2020-05-12
 */
@RestController
@RequestMapping("/audit")
@Validated
@Api(value = "审计", tags = {"审计操作接口"})
public class AuditController {

    @Autowired
    IAuditService auditService;

    @ApiOperation(value = "添加审计信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auditorId", value = "审核人id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "applicantId", value = "申请人id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "eventCode", value = "事件编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "event", value = "事件类型", dataType = "String", required = false),
            @ApiImplicitParam(name = "applyDate", value = "申请时间", dataType = "Date", required = false),
            @ApiImplicitParam(name = "auditDate", value = "审核时间", dataType = "Date", required = false),
            @ApiImplicitParam(name = "state", value = "审核状态", dataType = "String", required = false),
            @ApiImplicitParam(name = "comment", value = "备注", dataType = "String", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addAudit")
    public String addAudit(@RequestBody Audit audit){
        return auditService.addAudit(audit);
    }

    @ApiOperation(value = "更新审计信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auditSerial", value = "审核编号", dataType = "Long", required = true),
            @ApiImplicitParam(name = "auditorId", value = "审核人id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "applicantId", value = "申请人id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "eventCode", value = "事件编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "event", value = "事件类型", dataType = "String", required = false),
            @ApiImplicitParam(name = "applyDate", value = "申请时间", dataType = "Date", required = false),
            @ApiImplicitParam(name = "auditDate", value = "审核时间", dataType = "Date", required = false),
            @ApiImplicitParam(name = "state", value = "审核状态", dataType = "String", required = false),
            @ApiImplicitParam(name = "comment", value = "备注", dataType = "String", required = false)
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/updateAudit")
    public String updateAudit(@RequestBody @Validated Audit audit){
        return auditService.updateAudit(audit);
    }

    @ApiOperation(value = "获取审计信息")
    @ApiImplicitParam(name = "auditSerial", value = "审核编号", dataType = "Long", required = true)
    @GetMapping("/getAudit")
    public ReturnAuditVO getAudit(@NotNull(message = "请声明审核编号") Long auditSerial){
        return auditService.getAudit(auditSerial);
    }

    @ApiOperation(value = "删除审计信息")
    @ApiImplicitParam(name = "auditSerial", value = "审核编号", dataType = "Long", required = true)
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("/deleteAudit")
    public String deleteAudit(@NotNull(message = "请声明审核编号") Long auditSerial){
        return auditService.deleteAudit(auditSerial);
    }

    @ApiOperation(value = "列审计信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auditorId", value = "审核人id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "applicantId", value = "申请人id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "eventCode", value = "事件编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "event", value = "事件类型", dataType = "String", required = false),
            @ApiImplicitParam(name = "applyDate", value = "申请时间", dataType = "Date", required = false),
            @ApiImplicitParam(name = "auditDate", value = "审核时间", dataType = "Date", required = false),
            @ApiImplicitParam(name = "state", value = "审核状态", dataType = "String", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false)
    })
    @PostMapping("/listAudit")
    public List<ReturnAuditVO> listAudit(@RequestBody AuditVO audit){
        return auditService.listAudit(audit);
    }

//    @ApiOperation(value = "分页列审计信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "auditorId", value = "审核人id", dataType = "Long", required = false),
//            @ApiImplicitParam(name = "applicantId", value = "申请人id", dataType = "Long", required = false),
//            @ApiImplicitParam(name = "eventCode", value = "事件编号", dataType = "String", required = false),
//            @ApiImplicitParam(name = "event", value = "事件类型", dataType = "String", required = false),
//            @ApiImplicitParam(name = "applyDate", value = "申请时间", dataType = "Date", required = false),
//            @ApiImplicitParam(name = "auditDate", value = "审核时间", dataType = "Date", required = false),
//            @ApiImplicitParam(name = "state", value = "审核状态", dataType = "String", required = false),
//            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false),
//            @ApiImplicitParam(name = "currentPage", value = "当前页面", dataType = "Integer", required = true),
//            @ApiImplicitParam(name = "pageSize", value = "页面大小", dataType = "Integer", required = true)
//    })
//    @PostMapping("/pagedListAudit")
//    public IPage<Audit> pagedListAudit(@RequestBody @Validated AuditVO audit){
//        return auditService.pagedListAudit(audit);
//    }

}
