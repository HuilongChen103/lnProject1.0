package com.trainingmanagesys.web.salary.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.salary.validator.PagedListSalaryValidator;
import com.trainingmanagesys.web.salary.validator.UpdateSalaryValidator;
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
@GroupSequenceProvider(PagedListSalaryValidator.class)
public class SalaryVO implements Serializable, ValidationGroup {

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

    @NotNull(groups = listKeyGroup.class, message = "请指明当前页面")
    private Integer currentPage;

    @NotNull(groups = listKeyGroup.class, message = "请指明页面容量")
    private Integer pageSize;

}
