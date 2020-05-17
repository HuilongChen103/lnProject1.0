package com.trainingmanagesys.web.audit.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.audit.entity.Audit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.audit.vo.AuditVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-12
 */
public interface IAuditService extends IService<Audit> {

    String addAudit(Audit audit);

    String updateAudit(Audit audit);

    Audit getAudit(Long auditSerial);

    String deleteAudit(Long auditSerial);

    List<Audit> listAudit(AuditVO audit);

    IPage<Audit> pagedListAudit(AuditVO audit);
}
