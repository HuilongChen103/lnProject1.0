package com.trainingmanagesys.web.course.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.web.course.validator.UpdateCoursewareValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.group.GroupSequenceProvider;

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
@TableName("t_courseware")
@GroupSequenceProvider(UpdateCoursewareValidator.class)
public class Courseware implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课件流水号
     */
    @TableId(value = "courseware_serial", type = IdType.AUTO)
    @NotNull(groups = basicNotNullGroup.class, message = "请指明课件流水号")
    private Long coursewareSerial;

    /**
     * 班级号
     */
    @NotNull(groups = basicNotNullGroup.class, message = "请指明班级号")
    private String classCode;

    /**
     * 标题
     */
    @NotNull(groups = notAllNullGroup.class, message = "请输入信息，不能全部为空")
    private String title;

    /**
     * 文件序号
     */
    private Long fileSerial;


    public interface basicNotNullGroup{

    }

    public interface notAllNullGroup{

    }

}
