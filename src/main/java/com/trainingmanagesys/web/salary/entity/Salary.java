package com.trainingmanagesys.web.salary.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.salary.validator.UpdateSalaryValidator;
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
 * @since 2020-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_salary")
@GroupSequenceProvider(UpdateSalaryValidator.class)
public class Salary implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 工资流水号
     */
    @TableId(value = "salary_serial", type = IdType.AUTO)
    @NotNull(groups = addKeyGroup.class, message = "请指明工资流水号")
    private Long salarySerial;

    /**
     * 员工id
     */
    @NotNull(groups = updateGroup.class, message = "请输入信息，不能全部为空")
    private Long stuffId;

    /**
     * 基本工资
     */
    private Double basicSalary;

    /**
     * 奖金
     */
    private Double bonus;

    /**
     * 总工资
     */
    private Double totalSalary;

    /**
     * 五险一金数额
     */
    private Double insurance;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 年份
     */
    @NotNull(groups = yearNotNullGroup.class, message = "请指明年份")
    private Integer year;


}
