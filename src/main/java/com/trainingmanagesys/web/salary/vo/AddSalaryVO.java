package com.trainingmanagesys.web.salary.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.salary.validator.AddSalaryValidator;
import com.trainingmanagesys.web.salary.validator.UpdateSalaryValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 *  用于添加工资时的校验
 * </p>
 *
 * @author luoying
 * @since 2020-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@GroupSequenceProvider(AddSalaryValidator.class)
public class AddSalaryVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;


    /**
     * 员工id
     */
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
