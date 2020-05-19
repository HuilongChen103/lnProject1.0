package com.trainingmanagesys.web.goods.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("t_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 物资编号
     */
    @TableId(value = "goods_code")
    @NotNull(groups = basicNotNullGroup.class, message = "请指明物资编号")
    private String goodsCode;

    /**
     * 采办人id(person in charge)
     */
    @TableField("PIC_id")
    @NotNull(groups = notAllNullGroup.class, message = "请输入信息，不能全部为空")
    private Long picId;

    /**
     * 物资名称
     */
    @NotNull(groups = basicNotNullGroup.class, message = "请指明物资名称")
    private String name;

    /**
     * 类目
     */
    @NotNull(groups = basicNotNullGroup.class, message = "请指明物资类目")
    private String catagory;

    /**
     * 入库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date stockInDate;

    /**
     * 出库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date stockOutDate;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 现在的地点（房间号）
     */
    private Integer roomNum;

    /**
     * 备注
     */
    private String comment;


    public interface basicNotNullGroup{

    }

    public interface notAllNullGroup{

    }
}
