package com.trainingmanagesys.web.teacher.vo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2020-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TeacourseVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;


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
    private Long percentageMax;

    private Long percentageMin;

    /**
     * 学生留存率(实际学生数量/初始学生数量)
     */
    private Long remainMax;

    private Long remainMin;

    private Integer enable;

    private Integer limit;

    @NotNull(groups = listKeyGroup.class, message = "请指明当前页面")
    private Integer currentPage;

    @NotNull(groups = listKeyGroup.class, message = "请指明当前页面大小")
    private Integer pageSize;
}
