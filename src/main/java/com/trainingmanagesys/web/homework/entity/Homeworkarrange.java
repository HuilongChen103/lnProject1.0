package com.trainingmanagesys.web.homework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.web.homework.validator.UpdateHomeworkarrangeValidator;
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
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_homeworkarrange")
@GroupSequenceProvider(UpdateHomeworkarrangeValidator.class)
public class Homeworkarrange implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作业安排流水号
     */
    @TableId(value = "arrange_serial", type = IdType.AUTO)
    @NotNull(groups = basicNotNullGroup.class, message = "请指明作业安排流水号")
    private Long arrangeSerial;

    /**
     * 班级编号
     */
    @NotNull(groups = notAllNullGroup.class, message = "请输入信息，不能全部为空")
    private Long classCode;

    /**
     * 相关文件 可为null
     */
    private Long arrangeFile;

    /**
     * 作业内容文字说明
     */
    private String content;

    /**
     * 截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deadline;

    public interface basicNotNullGroup{

    }

    public interface notAllNullGroup{

    }
}
