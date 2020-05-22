package com.trainingmanagesys.web.recruit.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("t_recruitee")
public class Recruitee implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 受招募人流水号
     */
    @TableId(value = "recruitee_code")
    @NotNull(groups = addKeyGroup.class, message = "请输入受招募人流水号")
    private String recruiteeCode;

    /**
     * 招聘会编号
     */
    @NotNull(groups = addAdditionGroup.class, message = "请输入招聘会编号")
    private String recruitCode;

    /**
     * 名字
     */
    @NotNull(groups = updateGroup.class, message = "请输入信息，不能全部为空")
    private String name;

    /**
     * 简历文档
     */
    private Long resumeFile;

    /**
     * 类型（学生、职员、教师）
     */
    private String catagory;

    /**
     * 审核编号
     */
    private Long auditSerial;


}
