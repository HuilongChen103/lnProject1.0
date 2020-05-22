package com.trainingmanagesys.web.user.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PagedListUserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String position;

    private String state;

    private Integer enable;

    private Integer limit;

    @NotNull(message = "请指明当前页面")
    @Range(min = 1, max = 999)
    private Integer currentPage;

    @NotNull(message = "请指明当前页面")
    @Range(min = 1, max = 999)
    private Integer pageSize;
}
