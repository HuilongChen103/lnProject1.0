package com.trainingmanagesys.web.finance.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.finance.validator.UpdateFinanceValidator;
import com.trainingmanagesys.web.user.validator.UpdateUserValidator;
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
@TableName("t_finance")
@GroupSequenceProvider(UpdateFinanceValidator.class)
public class Finance implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 开支流水号
     */
    @TableId(value = "finance_code", type = IdType.AUTO)
    @NotNull(groups = addKeyGroup.class, message = "请声明开支流水号")
    private String financeCode;

    /**
     * EXP:支出 REV支出
     */
    @NotNull(groups = addAdditionGroup.class, message = "请注明是收入还是支出")
    private String inOut;

    /**
     * 负责人
     */
    @TableField("PIC_id")
    @NotNull(groups = addAdditionGroup.class, message = "请指明负责人")
    private Long picId;

    /**
     * 付款账户
     */
    @NotNull(groups = updateGroup.class, message = "请输入信息，不能全部为空")
    private String payAccount;

    /**
     * 收款账户
     */
    private String receiveAccount;

    /**
     * 交易方式
     */
    private String tradeMethod;

    /**
     * 数额
     */
    private Integer amount;

    /**
     * 日期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;

    /**
     * 类目
     */
    private String category;

    /**
     * 备注
     */
    private String comment;

}
