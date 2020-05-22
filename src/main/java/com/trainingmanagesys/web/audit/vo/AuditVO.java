package com.trainingmanagesys.web.audit.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.web.finance.validator.UpdateFinanceValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author luoying
 * @since 2020-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class AuditVO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 审核人id
     */
    private Long auditorId;

    /**
     * 申请人id
     */
    private Long applicantId;

    /**
     * 事件编号(招聘，物资调用...)
     */
    private String eventCode;

    /**
     * 事件类型
     */
    private String event;

    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyDate;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date auditDate;

    /**
     * 审核状态(通过，未通过...)
     */
    private String state;

    private Integer limit;

    @NotNull(message = "请指明当前页面")
    private Integer currentPage;

    @NotNull(message = "请指明页面大小")
    private Integer pageSize;

}
