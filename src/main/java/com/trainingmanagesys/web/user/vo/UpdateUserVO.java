package com.trainingmanagesys.web.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.web.user.entity.User;
import com.trainingmanagesys.web.user.validator.UpdateUserValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用于验证前端传过来的参数
 * 确保不是所有的参数都是空的，否则报错
 * </p>
 *
 * @author luoying
 * @since 2020-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@GroupSequenceProvider(UpdateUserValidator.class)
public class UpdateUserVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull(groups = userIdRequiredGroup.class, message = "uid不能为空")
    private Long uid;

    @NotNull(groups = notAllNullGroup.class, message = "不能全部为空")
    private String name;

    private String gender;

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;

    private String position;

    private Integer enable;

    @Length(min = 8, max = 10, message = "密码长度限制为8位到10位")
    private String password;


    public interface userIdRequiredGroup{

    }

    public interface notAllNullGroup{

    }

    public static User notAllNullUserVO2User(UpdateUserVO vo){
        User user = new User();
        user.setUid(vo.getUid());
        user.setName(vo.getName());
        user.setGender(vo.getGender());
        user.setBirthday(vo.getBirthday());
        user.setPosition(vo.getPosition());
        user.setEnable(vo.getEnable());
        user.setPassword(vo.getPassword());
        return user;
    }
}
