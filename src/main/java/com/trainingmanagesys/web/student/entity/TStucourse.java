package com.trainingmanagesys.web.student.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class TStucourse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * sc=student course流水号
     */
    @TableId(value = "sc_serial", type = IdType.AUTO)
    private Integer scSerial;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 课程编号
     */
    private String courseCode;

    /**
     * 班级编号
     */
    private String classCode;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 费用
     */
    private Integer fee;

    /**
     * 是否支付(已支付，未支付)
     */
    private String pay;

    /**
     * 收支编号
     */
    private String financeCode;


}
