package com.trainingmanagesys.web.teacher.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Table;

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
public class Teacourse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * tc=teacher course流水号
     */
    @TableId(value = "tc_serial", type = IdType.AUTO)
    private Long tcSerial;

    /**
     * 教师id
     */
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
