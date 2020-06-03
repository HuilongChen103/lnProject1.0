package com.trainingmanagesys.web.room.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.room.validator.UpdateRoomValidator;
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
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_room")
@GroupSequenceProvider(UpdateRoomValidator.class)
public class Room implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 房间号
     */
    @TableId(value = "room_num")
    @NotNull(groups = addKeyGroup.class, message = "请指明房间号")
    private Long roomNum;

    /**
     * 用途（教室，库房，办公室等）
     */
    @NotNull(groups = updateGroup.class, message = "请输入信息，不能全部为空")
    private String roomUsage;

    /**
     * 是否可用（0：可用 1：占用）
     */
    private Integer available;

}
