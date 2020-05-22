package com.trainingmanagesys.web.grade.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
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
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_grade")
public class Grade implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 分数流水号
     */
    @TableId(value = "grade_serial", type = IdType.AUTO)
    @NotNull(groups = addKeyGroup.class, message = "请指明分数流水号")
    private Long gradeSerial;

    /**
     * 考试号
     */
    @NotNull(groups = addAdditionGroup.class, message = "请指明考试号")
    private Long testSerial;

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 分数
     */
    private Long grade;

    /**
     * 备注
     */
    private String comment;


}
