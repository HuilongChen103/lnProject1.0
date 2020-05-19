package com.trainingmanagesys.web.benefitevaluation.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.web.benefitevaluation.validator.PagedListBenefitevaluationValidator;
import com.trainingmanagesys.web.benefitevaluation.validator.UpdateBenefitevaluationValidator;
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
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@GroupSequenceProvider(PagedListBenefitevaluationValidator.class)
public class BenefitevaluationVO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 员工id（职员、教师）
     */
    private Long stuffId;

    /**
     * 年份
     */
    @NotNull(groups = yearNotNullGroup.class, message = "请指明年份")
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 效益（具体金额）
     */
    private Long benefitMax;

    private Long benefitMin;

    /**
     * 考评（文字说明）
     */
    private String assessment;

    private Integer limit;

    @NotNull(groups = basicNotNullGroup.class, message = "请指定当前页面")
    private Integer currentPage;

    @NotNull(groups = basicNotNullGroup.class, message = "请指定页面大小")
    private Integer pageSize;


    public interface basicNotNullGroup{

    }

    public interface yearNotNullGroup{

    }
}
