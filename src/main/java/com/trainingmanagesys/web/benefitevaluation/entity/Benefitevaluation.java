package com.trainingmanagesys.web.benefitevaluation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

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
@TableName("t_benefitevaluation")
public class Benefitevaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 绩效考评流水号
     */
    @TableId(value = "benefit_serial", type = IdType.AUTO)
    @NotNull(groups = basicNotNullGroup.class, message = "请指明绩效考评流水号")
    private Long benefitSerial;

    /**
     * 员工id（职员、教师）
     */
    @NotNull(groups = notAllNullGroup.class, message = "请输入信息，不能全部为空")
    private Long stuffId;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 效益（具体金额）
     */
    private Long benefit;

    /**
     * 考评（文字说明）
     */
    private String assessment;


    public interface basicNotNullGroup{

    }

    public interface notAllNullGroup{

    }
}
