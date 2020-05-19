package com.trainingmanagesys.web.assessment.entity;

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
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_assessment")
public class Assessment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 测评流水号
     */
    @TableId(value = "assess_serial", type = IdType.AUTO)
    @NotNull(groups = basicNotNullGroup.class, message = "请声明测评流水号")
    private Long assessSerial;

    /**
     * 被测者id
     */
    @NotNull(groups = basicNotNullGroup.class, message = "请声明被测者id")
    private Long targetId;

    /**
     * 测评者id
     */
    @NotNull(groups = notAllNullGroup.class, message = "请输入信息，不能全部为空")
    private Long assessorId;

    /**
     * 学期
     */
    private String semester;

    /**
     * 学年
     */
    @NotNull(groups = semesterWithYearGroup.class, message = "请声明学年")
    private Integer year;

    /**
     * 事件编号(班级，招聘，活动等)
     */
    private String eventCode;

    /**
     * 分数
     */
    private Long grade;

    /**
     * 评价
     */
    private String comment;


    public interface basicNotNullGroup{

    }

    public interface notAllNullGroup{

    }

    public interface semesterWithYearGroup{

    }
}
