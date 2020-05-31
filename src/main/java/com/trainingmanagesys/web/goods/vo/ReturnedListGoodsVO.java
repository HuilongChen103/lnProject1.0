package com.trainingmanagesys.web.goods.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.goods.validator.UpdateGoodsValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

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
public class ReturnedListGoodsVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 物资编号
     */
    private String goodsCode;

    /**
     * 采办人id(person in charge)
     */
    private Long picId;

    /**
     * 物资名称
     */
    private String name;

    /**
     * 类目
     */
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
    private Double price;

    /**
     * 现在的地点（房间号）
     */
    private Integer roomNum;

    /**
     * 备注
     */
    private String comment;

    private String usage;
}
