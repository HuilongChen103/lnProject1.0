package com.trainingmanagesys.web.student.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.web.student.validator.UpdateStucourseValidator;
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
@TableName("t_stucourse")
@GroupSequenceProvider(UpdateStucourseValidator.class)
public class Stucourse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * sc=student course流水号
     */
    @TableId(value = "sc_serial", type = IdType.AUTO)
    @NotNull(groups = basicNotNullGroup.class, message = "请指明学生课程流水号")
    private Long scSerial;

    /**
     * 学生id
     */
    @NotNull(groups = notAllNullGroup.class, message = "请输入信息，不能全部为空")
    private Long studentId;

    /**
     * 课程编号
     */
    private String courseCode;

    /**
     * 班级编号
     */
    private String classCode;

    /**
     * 费用
     */
    private Long fee;

    /**
     * 是否支付(已支付，未支付)
     */
    private String pay;

    /**
     * 收支编号
     */
    private String financeCode;


    public interface basicNotNullGroup{

    }

    public interface notAllNullGroup{

    }

}
