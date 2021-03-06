package com.trainingmanagesys.web.messageboard.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.messageboard.validator.UpdateMessageValidator;
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
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_messageboard")
@GroupSequenceProvider(UpdateMessageValidator.class)
public class Messageboard implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 留言流水号
     */
    @TableId(value = "message_serial", type = IdType.AUTO)
    @NotNull(groups = addKeyGroup.class, message = "请指明留言流水号")
    private Long messageSerial;

    /**
     * 上传人id
     */
    @NotNull(groups = addAdditionGroup.class, message = "请指明上传人id")
    private Long uploaderId;

    /**
     * 内容
     */
    @NotNull(groups = updateGroup.class, message = "请输入信息，不能全部为空")
    private String content;

    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;

    /**
     * 班级号
     */
    private String classCode;

    /**
     * 要回复的留言的流水号
     */
    private Long reply;
}
