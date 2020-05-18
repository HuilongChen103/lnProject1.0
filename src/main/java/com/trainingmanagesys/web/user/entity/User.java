package com.trainingmanagesys.web.user.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.web.user.validator.AddUserValidator;
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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    @NotNull(groups = userIdRequiredGroup.class, message = "请提供uid")
    private Long uid;

    @NotNull(groups = allNotNullGroup.class, message = "请输入信息，不能全部为空")
    private String name;

    private String gender;

    @Past(message = "日期输入错误")   // 生日日期必须在当前时间之前
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;

    private String position;

    private String password;

    private String state;

    private Integer enable;

    private String tel;

    /**
     * 定义专属验证逻辑分组
     */
    public interface userIdRequiredGroup{

    }

    public interface allNotNullGroup{

    }
}
