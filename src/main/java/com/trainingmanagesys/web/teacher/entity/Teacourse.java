package com.trainingmanagesys.web.teacher.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.teacher.validator.UpdateTeacourseValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Table;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author luoying
 * @since 2020-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_teacourse")
@GroupSequenceProvider(UpdateTeacourseValidator.class)
public class Teacourse implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * tc=teacher course流水号
     */
    @TableId(value = "tc_serial", type = IdType.AUTO)
    @NotNull(groups = addKeyGroup.class, message = "请指明教师课程流水号")
    private Long tcSerial;

    /**
     * 教师id
     */
    @NotNull(groups = updateGroup.class, message = "请指明信息，不能全部为空")
    private Long teacherId;

    /**
     * 课程编号
     */
    private String courseCode;

    /**
     * 班级编号
     */
    private String classCode;

    /**
     * 班级表现(学生均分/满分)
     */
    private Long percentage;

    /**
     * 学生留存率(实际学生数量/初始学生数量)
     */
    private Long remain;

    /**
     * 教师个人介绍，课程介绍。
     */
    private String intro;
}
