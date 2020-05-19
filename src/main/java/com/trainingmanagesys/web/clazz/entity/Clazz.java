package com.trainingmanagesys.web.clazz.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("t_class")
public class Clazz implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级号
     */
    @TableId(value = "class_code")
    @NotNull(groups = basicNotNullGroup.class, message = "请指明班级号")
    private String classCode;

    /**
     * 课程号
     */
    @NotNull(groups = notAllNullGroup.class, message = "请输入信息，不能全部为空")
    private String courseCode;

    /**
     * 起始学生数量(开学的时候)
     */
    private Integer studentNum;

    /**
     * 实际学生数量
     */
    private Integer realNum;

    /**
     * 教师id
     */
    private Long teacherId;

    /**
     * 班级在对应课程中的序号
     */
    private Long classNum;

    /**
     * 日程安排编号
     */
    private Long scheduleSerial;


    public interface basicNotNullGroup{

    }

    public interface notAllNullGroup{

    }
}
