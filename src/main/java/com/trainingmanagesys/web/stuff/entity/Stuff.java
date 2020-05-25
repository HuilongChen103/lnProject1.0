package com.trainingmanagesys.web.stuff.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.stuff.validator.UpdateStuffValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.group.GroupSequenceProvider;

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
@TableName("t_stuff")
@GroupSequenceProvider(UpdateStuffValidator.class)
public class Stuff implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 职工id
     */
    @TableId(value = "stuff_id")
    @NotNull(groups = Stuff.addKeyGroup.class, message = "请指明职工id")
    private Long stuffId;

    /**
     * 部门id
     */
    @NotNull(groups = {Stuff.addAdditionGroup.class}, message = "请指明部门id")
    private Long departmentId;

    /**
     * 职位
     */

    @NotNull(groups = Stuff.updateGroup.class, message = "请输入信息不能全部为空")
    private String position;


}
