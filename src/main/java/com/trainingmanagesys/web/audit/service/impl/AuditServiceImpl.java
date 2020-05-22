package com.trainingmanagesys.web.audit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.audit.entity.Audit;
import com.trainingmanagesys.web.audit.dao.AuditMapper;
import com.trainingmanagesys.web.audit.service.IAuditService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.audit.vo.AuditVO;
import org.springframework.stereotype.Service;

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

    public Audit checkAuditExistence(Long auditSerial){
        Audit tempAudit = getAudit(auditSerial);
        if (tempAudit == null){
            APIException apiException = new APIException("该审计不存在");
            throw apiException;
        }
        return tempAudit;
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
    public Audit getAudit(Long auditSerial) {
        checkAuditExistence(auditSerial);
        return baseMapper.selectById(auditSerial);
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
    public List<Audit> listAudit(AuditVO audit) {
        QueryWrapper<Audit> queryWrapper = new QueryWrapper<>();
        if (audit.getAuditorId() != null) queryWrapper.eq("auditor_id", audit.getAuditorId());
        if (audit.getApplicantId() != null) queryWrapper.eq("applicant_id", audit.getApplicantId());
        if (audit.getEventCode() != null) queryWrapper.eq("event_code", audit.getEventCode());
        if (audit.getEvent() != null) queryWrapper.eq("event", audit.getEvent());
        if (audit.getApplyDate() != null) queryWrapper.eq("apply_date", audit.getApplyDate());
        if (audit.getAuditDate() != null) queryWrapper.eq("audit_date", audit.getAuditDate());
        if (audit.getState() != null) queryWrapper.eq("state", audit.getState());
        if (audit.getLimit() != null) queryWrapper.last(" limit " + audit.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Audit> pagedListAudit(AuditVO audit) {
        QueryWrapper<Audit> queryWrapper = new QueryWrapper<>();
        if (audit.getAuditorId() != null) queryWrapper.eq("auditor_id", audit.getAuditorId());
        if (audit.getApplicantId() != null) queryWrapper.eq("applicant_id", audit.getApplicantId());
        if (audit.getEventCode() != null) queryWrapper.eq("event_code", audit.getEventCode());
        if (audit.getEvent() != null) queryWrapper.eq("event", audit.getEvent());
        if (audit.getApplyDate() != null) queryWrapper.eq("apply_date", audit.getApplyDate());
        if (audit.getAuditDate() != null) queryWrapper.eq("audit_date", audit.getAuditDate());
        if (audit.getState() != null) queryWrapper.eq("state", audit.getState());
        if (audit.getLimit() != null) queryWrapper.last(" limit " + audit.getLimit());
        Page<Audit> page = new Page<>();
        page.setCurrent(audit.getCurrentPage());
        page.setSize(audit.getPageSize());
        IPage<Audit> pagedList = baseMapper.selectPage(page, queryWrapper);
        return pagedList;
    }
}
