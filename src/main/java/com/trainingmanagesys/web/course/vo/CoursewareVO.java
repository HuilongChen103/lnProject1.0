package com.trainingmanagesys.web.course.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.course.validator.UpdateCoursewareValidator;
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
public class CoursewareVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 班级号
     */
    private String classCode;

    /**
     * 标题
     */
    private String title;

    /**
     * 文件序号
     */
    private Long fileSerial;

    private Integer limit;

    @NotNull(groups = listKeyGroup.class, message = "请输入当前页面")
    private Integer currentPage;

    @NotNull(groups = listKeyGroup.class, message = "请输入页面大小")
    private Integer pageSize;
}
