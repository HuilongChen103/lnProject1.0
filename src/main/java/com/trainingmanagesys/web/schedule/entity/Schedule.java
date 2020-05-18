package com.trainingmanagesys.web.schedule.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.web.schedule.validator.UpdateScheduleValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * <p>
 * 
 * </p>
 *
 * @author luoying
 * @since 2020-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_schedule")
@GroupSequenceProvider(UpdateScheduleValidator.class)
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日程id
     */
    @TableId(value = "schedule_serial", type = IdType.AUTO)
    @NotNull(groups = basicNotNullGroup.class, message = "请指明scheduleId")
    private Long scheduleSerial;

    /**
     * 事件编号
     */
    @NotNull(groups = notAllNullGroup.class, message = "请输入信息，不能全部为空")
    private String eventCode;

    /**
     * 时间段
     */
    @Past(message = "输入时间错误")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    /**
     * 周数
     */
    private Integer week;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 季度
     */
    private String semester;


    public interface basicNotNullGroup{

    }

    public interface notAllNullGroup{

    }
}
