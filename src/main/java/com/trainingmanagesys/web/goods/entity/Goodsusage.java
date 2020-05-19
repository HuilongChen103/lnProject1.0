package com.trainingmanagesys.web.goods.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_goodsusage")
public class Goodsusage implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 使用编号
     */
    @NotNull(groups = basicNotNullGroup.class, message = "请指明使用编号")
    private String usageCode;

    /**
     * 器材编号
     */
    @NotNull(groups = basicNotNullGroup.class, message = "请指明器材编号")
    private String goodsCode;

    /**
     * 租借者id（若为空，则是官方分配，比如教室的桌椅）
     */
    @NotNull(groups = notAllNullGroup.class, message = "请输入信息，不能全部为空")
    private Long rentorId;

    /**
     * 负责人id（person in charge）
     */
    @TableField("PIC_id")
    private Long picId;

    /**
     * 租借时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date rentDate;

    /**
     * 归还时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date returnDate;

}
