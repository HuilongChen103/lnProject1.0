package com.trainingmanagesys.web.assessment.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
public class AssessmentVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 测评流水号
     */
    @NotNull(groups = addKeyGroup.class, message = "请声明测评流水号")
    private Long assessSerial;

    /**
     * 被测者id
     */
    private Long targetId;

    /**
     * 测评者id
     */
    private Long assessorId;

    /**
     * 学期
     */
    private String semester;

    /**
     * 学年
     */
    @NotNull(groups = yearNotNullGroup.class, message = "请声明学年")
    private Integer year;

    /**
     * 事件编号(班级，招聘，活动等)
     */
    private String eventCode;

    private Integer limit;

    @NotNull(groups = listKeyGroup.class, message = "请指明当前页面")
    private Integer currentPage;

    @NotNull(groups = listKeyGroup.class , message = "请指明页面大小")
    private Integer pageSize;
}
