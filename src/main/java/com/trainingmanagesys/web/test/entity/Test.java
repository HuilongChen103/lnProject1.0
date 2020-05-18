package com.trainingmanagesys.web.test.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("t_test")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 考试号
     */
    @TableId(value = "test_serial", type = IdType.AUTO)
    private Long testSerial;

    /**
     * 班级号
     */
    private String classCode;

    /**
     * 监考人甲
     */
    private Long testerId1;

    /**
     * 监考人乙
     */
    private Long testerId2;

    /**
     * 考试内容文件
     */
    private Long testFile;

    /**
     * 日程安排流水号
     */
    private Long scheduleSerial;


}
