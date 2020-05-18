package com.trainingmanagesys.web.homework.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.web.homework.validator.UpdateHomeworkValidator;
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
 * @since 2020-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_homework")
@GroupSequenceProvider(UpdateHomeworkValidator.class)
public class Homework implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作业流水号
     */
    @TableId(value = "hw_serial", type = IdType.AUTO)
    @NotNull(groups = basicNotNullGroup.class, message = "请指明作业流水号")
    private Long hwSerial;

    /**
     * 作业安排流水号
     */
    @NotNull(groups = notAllNullGroup.class, message = "请输入信息，不能全部为空")
    private Long arrangeSerial;

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 作业文件
     */
    private Long hwFile;

    /**
     * 分数
     */
    private String grade;


    public interface basicNotNullGroup{

    }

    public interface notAllNullGroup{

    }
}
