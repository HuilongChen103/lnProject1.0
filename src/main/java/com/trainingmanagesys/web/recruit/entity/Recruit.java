package com.trainingmanagesys.web.recruit.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.recruit.validator.UpdateRecruitValidator;
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
 * @since 2020-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_recruit")
@GroupSequenceProvider(UpdateRecruitValidator.class)
public class Recruit implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 招聘编号
     */
    @TableId(value = "recruit_code")
    @NotNull(groups = addKeyGroup.class, message = "请指明招聘编号")
    private String recruitCode;

    /**
     * 主办人id
     */
    @TableField("PIC_id")
    @NotNull(groups = updateGroup.class, message = "请输入信息，不能全部为空")
    private Long picId;

    /**
     * 日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;

    /**
     * 地点
     */
    private String place;

    /**
     * 方式（网络，实地等）
     */
    private String method;

    /**
     * 招聘对象类型（教师，学生，职工）
     */
    private String catagory;

    /**
     * 备注
     */
    private String comment;


}
