package com.trainingmanagesys.web.goods.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.utils.ValidationGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class GoodsVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 采办人id(person in charge)
     */
    @TableField("PIC_id")
    @NotNull(groups = updateGroup.class, message = "请输入信息，不能全部为空")
    private Long picId;

    /**
     * 物资名称
     */
    @NotNull(groups = addAdditionGroup.class, message = "请指明物资名称")
    private String name;

    /**
     * 类目
     */
    @NotNull(groups = addAdditionGroup.class, message = "请指明物资类目")
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
    private Double priceMax;

    private Double priceMin;

    /**
     * 现在的地点（房间号）
     */
    private Integer roomNum;

    private Integer limit;

    @NotNull(groups = listKeyGroup.class, message = "请指明当前页面")
    private Integer currentPage;

    @NotNull(groups = listKeyGroup.class, message = "请指明页面大小")
    private Integer pageSize;

}
