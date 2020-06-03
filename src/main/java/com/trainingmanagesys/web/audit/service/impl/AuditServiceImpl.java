package com.trainingmanagesys.web.audit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.utils.BaseConst;
import com.trainingmanagesys.web.audit.entity.Audit;
import com.trainingmanagesys.web.audit.dao.AuditMapper;
import com.trainingmanagesys.web.audit.service.IAuditService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.audit.vo.AuditVO;
import com.trainingmanagesys.web.audit.vo.ReturnAuditVO;
import com.trainingmanagesys.web.user.entity.User;
import com.trainingmanagesys.web.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-12
 */
@Service
public class AuditServiceImpl extends ServiceImpl<AuditMapper, Audit> implements IAuditService {

    @Autowired
    IUserService userService;

    public Audit checkAuditExistence(Long auditSerial){
        Audit tempAudit = baseMapper.selectById(auditSerial);
        if (tempAudit == null){
            APIException apiException = new APIException("该审计不存在");
            throw apiException;
        }
        return tempAudit;
    }

    public ReturnAuditVO audit2ReturnAuditVO(Audit audit){
        ReturnAuditVO vo = new ReturnAuditVO();
        vo.setAuditSerial(audit.getAuditSerial());
        vo.setAuditorId(audit.getAuditorId());
        vo.setApplicantId(audit.getApplicantId());
        vo.setEventCode(audit.getEventCode());
        vo.setEvent(audit.getEvent());
        vo.setApplyDate(audit.getApplyDate());
        vo.setAuditDate(audit.getAuditDate());
        vo.setState(audit.getState());
        vo.setComment(audit.getComment());

        User auditUser = userService.getUser(vo.getAuditorId(), BaseConst.DATA_ENABLE);
        User applicatentUser = userService.getUser(vo.getApplicantId(), BaseConst.DATA_ENABLE);
        vo.setAuditorName(auditUser.getName());
        vo.setApplicantName(applicatentUser.getName());
        return vo;
    }

    @Override
    public String addAudit(Audit audit) {
        String result = "添加审计失败";
        int code = baseMapper.insert(audit);
        if (code == 1)
            result = "添加审计成功";
        return result;

    }

    @Override
    public String updateAudit(Audit audit) {
        checkAuditExistence(audit.getAuditSerial());
        String result = "更新审计失败";
        int code = baseMapper.updateById(audit);
        if (code == 1)
            result = "更新审计成功";
        return result;
    }

    @Override
    public ReturnAuditVO getAudit(Long auditSerial) {
        checkAuditExistence(auditSerial);
        Audit temp = baseMapper.selectById(auditSerial);
        return audit2ReturnAuditVO(temp);
    }

    @Override
    public String deleteAudit(Long auditSerial) {
        checkAuditExistence(auditSerial);
        String result = "删除审计失败";
        int code = baseMapper.deleteById(auditSerial);
        if (code == 1)
            result = "删除审计成功";
        return result;
    }

    @Override
    public List<ReturnAuditVO> listAudit(AuditVO audit) {
        QueryWrapper<Audit> queryWrapper = new QueryWrapper<>();
        if (audit.getAuditorId() != null) queryWrapper.eq("auditor_id", audit.getAuditorId());
        if (audit.getApplicantId() != null) queryWrapper.eq("applicant_id", audit.getApplicantId());
        if (audit.getEventCode() != null) queryWrapper.eq("event_code", audit.getEventCode());
        if (audit.getEvent() != null) queryWrapper.eq("event", audit.getEvent());
        if (audit.getApplyDate() != null) queryWrapper.eq("apply_date", audit.getApplyDate());
        if (audit.getAuditDate() != null) queryWrapper.eq("audit_date", audit.getAuditDate());
        if (audit.getState() != null) queryWrapper.eq("state", audit.getState());
        if (audit.getLimit() != null) queryWrapper.last(" limit " + audit.getLimit());
        List<Audit> tempList = baseMapper.selectList(queryWrapper);
        List<ReturnAuditVO> resultList = null;
        for (Audit item : tempList){
            resultList.add(audit2ReturnAuditVO(item));
        }
        return resultList;
    }

//    @Override
//    public IPage<Audit> pagedListAudit(AuditVO audit) {
//        QueryWrapper<Audit> queryWrapper = new QueryWrapper<>();
//        if (audit.getAuditorId() != null) queryWrapper.eq("auditor_id", audit.getAuditorId());
//        if (audit.getApplicantId() != null) queryWrapper.eq("applicant_id", audit.getApplicantId());
//        if (audit.getEventCode() != null) queryWrapper.eq("event_code", audit.getEventCode());
//        if (audit.getEvent() != null) queryWrapper.eq("event", audit.getEvent());
//        if (audit.getApplyDate() != null) queryWrapper.eq("apply_date", audit.getApplyDate());
//        if (audit.getAuditDate() != null) queryWrapper.eq("audit_date", audit.getAuditDate());
//        if (audit.getState() != null) queryWrapper.eq("state", audit.getState());
//        if (audit.getLimit() != null) queryWrapper.last(" limit " + audit.getLimit());
//        Page<Audit> page = new Page<>();
//        page.setCurrent(audit.getCurrentPage());
//        page.setSize(audit.getPageSize());
//        IPage<Audit> pagedList = baseMapper.selectPage(page, queryWrapper);
//        return pagedList;
//    }
}
