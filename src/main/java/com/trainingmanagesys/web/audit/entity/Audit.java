package com.trainingmanagesys.web.audit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.web.finance.validator.UpdateFinanceValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;

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
@TableName("t_audit")
@GroupSequenceProvider(UpdateFinanceValidator.class)
public class Audit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 审核编号
     */
    @TableId(value = "audit_serial", type = IdType.AUTO)
    @NotNull(groups = basicNotNullGroup.class, message = "请输入审核编号")
    private Long auditSerial;

    /**
     * 审核人id
     */
    @NotNull(groups = notAllNullGroup.class, message = "请输入信息，不能全部为空")
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

    /**
     * 备注
     */
    private String comment;


    public interface basicNotNullGroup{

    }

    public interface notAllNullGroup{

    }


}
