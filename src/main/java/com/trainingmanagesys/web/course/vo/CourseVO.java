package com.trainingmanagesys.web.course.vo;

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
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 最大学生数量
     */
    // 最大学生数量的最小值，用于列出最大学生数量比这个大的
    private Integer studentMaxMin;

    private Integer studentMaxMax;

    /**
     * 类型
     */
    private String type;

    private Integer limit;

    @NotNull(groups = addKeyGroup.class, message = "请指明当前页面")
    private Integer currentPage;

    @NotNull(groups = addKeyGroup.class, message = "请指明页面大小")
    private Integer pageSize;
}
