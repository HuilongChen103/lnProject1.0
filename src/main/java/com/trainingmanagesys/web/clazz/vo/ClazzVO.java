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
     * 课程名字(属于外键t_course)
     */
    private String courseName;

    /**
     * 教师id
     */
    private Long teacherId;


    /**
     * 日程安排编号
     */
    private Long scheduleSerial;

    private Long roomNum;

    private Integer limit;

    private Integer enable;

    @NotNull(groups = listKeyGroup.class, message = "请指明当前页面")
    private Integer currentPage;

    @NotNull(groups = listKeyGroup.class, message = "请指明页面大小")
    private Integer pageSize;
}
