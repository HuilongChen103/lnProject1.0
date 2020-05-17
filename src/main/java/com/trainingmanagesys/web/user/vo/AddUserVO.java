package com.trainingmanagesys.web.user.vo;

import com.trainingmanagesys.web.user.entity.User;
import com.trainingmanagesys.web.user.validator.AddUserValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 用于验证前端传过来的参数
 * 确保所有的参数都不是空的
 * </p>
 *
 * @author luoying
 * @since 2020-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@GroupSequenceProvider(AddUserValidator.class)
public class AddUserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(groups = allNotNullGroup.class, message = "名字不能为空")
    private String name;

    @NotNull(groups = allNotNullGroup.class, message = "密码不能为空")
    private String password;

    public interface allNotNullGroup{

    }

    public static User allNotNullUserVO2User(AddUserVO vo){
        User user = new User();
        user.setName(vo.getName());
        user.setPassword(vo.getPassword());
        return user;
    }
}
