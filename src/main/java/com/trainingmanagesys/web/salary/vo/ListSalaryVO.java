package com.trainingmanagesys.web.salary.vo;

import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.salary.validator.ListSalaryValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
@GroupSequenceProvider(ListSalaryValidator.class)
public class ListSalaryVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 员工id
     */
    private Long stuffId;


    /**
     * 月份
     */
    private Integer month;

    /**
     * 年份
     */
    @NotNull(groups = yearNotNullGroup.class, message = "请指明年份")
    private Integer year;

    private Integer limit;

}
