package com.trainingmanagesys.web.clazz.vo;

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
public class ReturnedListClazzVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 班级号
     */
    private String classCode;

    /**
     * 课程号
     */
    private String courseCode;

    /**
     * 课程名称
     */
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
    private String name;

    /**
     * 日程安排编号
     */
    private Long scheduleSerial;

    private String scheduleTime;
}
