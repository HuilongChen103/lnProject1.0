package com.trainingmanagesys.web.finance.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.utils.ValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用于列Finance对象
 */

@Data
public class FinanceVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;


    /**
     * EXP:支出 REV支出
     */
    private String inOut;

    /**
     * 负责人
     */
    private Long picId;

    /**
     * 付款账户
     */
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
    private Integer minAmount;

    private Integer maxAmount;

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

    private Integer limit;

    @NotNull(groups = listKeyGroup.class, message = "请指明当前页面")
    private Integer currentPage;

    @NotNull(groups = listKeyGroup.class, message = "请指明页面大小")
    private Integer pageSize;

}
