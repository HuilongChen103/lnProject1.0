package com.trainingmanagesys.web.stuff.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
public class StuffVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;


    /**
     * 部门id
     */
    private Long departmentId;

    /**
     * 职位
     */

    private String position;

    private Integer limit;

    @NotNull(groups = listKeyGroup.class, message = "请指明当前页面")
    private Integer currentPage;

    @NotNull(groups = listKeyGroup.class, message = "请指明页面容量")
    private Integer pageSize;

}
