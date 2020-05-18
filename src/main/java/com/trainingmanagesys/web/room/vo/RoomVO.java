package com.trainingmanagesys.web.room.vo;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoomVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用途（教室，库房，办公室等）
     */
    private String usage;

    /**
     * 是否可用（0：可用 1：占用）
     */
    private Integer available;

    private Integer limit;

    @NotNull(groups = basicNotNullGroup.class, message = "请指明当前页面")
    private Integer currentPage;

    @NotNull(groups = basicNotNullGroup.class, message = "请指明页面大小")
    private Integer pageSize;

    public interface basicNotNullGroup{

    }

}
