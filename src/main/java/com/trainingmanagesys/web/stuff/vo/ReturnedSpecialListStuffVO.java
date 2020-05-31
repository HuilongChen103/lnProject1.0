package com.trainingmanagesys.web.stuff.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.stuff.entity.Stuff;
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
public class ReturnedSpecialListStuffVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 职工id
     */
    private Long stuffId;

    private String name;

    private Long departmentId;

    private String departmentName;

    private String position;

}
