package com.trainingmanagesys.web.homework.entity;

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
public class THomework implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作业流水号
     */
    private Integer hwSerial;

    /**
     * 作业安排流水号
     */
    private String arrangeSerial;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 作业文件
     */
    private Integer hwFile;

    /**
     * 分数
     */
    private String grade;


}
