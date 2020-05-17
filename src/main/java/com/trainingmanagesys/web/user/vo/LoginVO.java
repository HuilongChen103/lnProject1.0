package com.trainingmanagesys.web.user.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "uid不能为空")
    private Long uid;

    @NotNull(message = "密码不能为空")
    private String password;
}
