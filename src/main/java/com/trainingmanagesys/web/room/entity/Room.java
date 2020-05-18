package com.trainingmanagesys.web.room.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 房间号
     */
    @TableId(value = "room_num")
    @NotNull(groups = basicNotNullGroup.class, message = "请指明房间号")
    private Long roomNum;

    /**
     * 用途（教室，库房，办公室等）
     */
    private String usage;

    /**
     * 是否可用（0：可用 1：占用）
     */
    private Integer available;


    public interface basicNotNullGroup{

    }
}
