package com.trainingmanagesys.web.department.entity;

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
 * @since 2020-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_department")
public class Department implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    @TableId(value = "department_id")
    @NotNull(groups = addKeyGroup.class, message = "请指明部门id")
    private Long departmentId;

    /**
     * 部门名称
     */
    @NotNull(groups = updateGroup.class, message = "请输入部门名称")
    private String name;


}
