package com.trainingmanagesys.web.course.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.course.validator.UpdateCourseValidator;
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
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_course")
@GroupSequenceProvider(UpdateCourseValidator.class)
public class Course implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 课程编号
     */
    @TableId(value = "course_code")
    @NotNull(groups = addKeyGroup.class, message = "请指明课程编号")
    private String courseCode;

    /**
     * 课程名称
     */
    @NotNull(groups = updateGroup.class, message = "请输入信息，不能全部为空")
    private String courseName;

    /**
     * 课程时长
     */
    private String duration;

    /**
     * 类型
     */
    private String type;

    /**
     * 备注
     */
    private String comment;

    private Double fee;
}
