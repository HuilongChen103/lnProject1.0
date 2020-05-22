package com.trainingmanagesys.web.user.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.user.validator.UpdateUserValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * <p>
 * 
 * </p>
 *
 * @author luoying
 * @since 2020-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
@GroupSequenceProvider(UpdateUserValidator.class)
public class User implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    @NotNull(groups = addKeyGroup.class, message = "请提供uid")
    private Long uid;

    @NotNull(groups = addAdditionGroup.class, message = "请输入姓名")
    private String name;

    @NotNull(groups = updateGroup.class, message = "请输入信息，不能全部为空")
    private String gender;

    @Past(message = "日期输入错误")   // 生日日期必须在当前时间之前
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;

    private String position;

    @NotNull(groups = addAdditionGroup.class, message = "请输入密码")
    private String password;

    private String state;

    private Integer enable;

    private String tel;

}
