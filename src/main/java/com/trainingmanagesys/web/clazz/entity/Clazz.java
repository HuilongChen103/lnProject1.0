package com.trainingmanagesys.web.clazz.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.clazz.validator.UpdateClazzValidator;
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
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_class")
@GroupSequenceProvider(UpdateClazzValidator.class)
public class Clazz implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 班级号
     */
    @TableId(value = "class_code")
    @NotNull(groups = addKeyGroup.class, message = "请指明班级号")
    private String classCode;

    /**
     * 课程号
     */
    @NotNull(groups = updateGroup.class, message = "请输入信息，不能全部为空")
    private String courseCode;

    /**
     * 课程名称
     */
    @TableField(exist = false)
    private String courseName;

    /**
     * 起始学生数量(开学的时候)
     */
    private Integer studentNum;

    /**
     * 实际学生数量
     */
    private Integer realNum;

    /**
     * 最大学生数量
     */
    private Integer studentMax;

    /**
     * 教师id
     */
    private Long teacherId;

    /**
     * 教师名字
     */
    @TableField(exist = false)
    private String name;

    /**
     * 日程安排编号
     */
    private Long scheduleSerial;
}
