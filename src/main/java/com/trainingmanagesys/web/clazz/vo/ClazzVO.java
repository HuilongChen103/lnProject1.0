package com.trainingmanagesys.web.clazz.vo;

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
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ClazzVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 课程号
     */
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

    private Integer limit;

    @NotNull(groups = listKeyGroup.class, message = "请指明当前页面")
    private Integer currentPage;

    @NotNull(groups = listKeyGroup.class, message = "请指明页面大小")
    private Integer pageSize;
}