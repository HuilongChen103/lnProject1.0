package com.trainingmanagesys.web.schedule.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.utils.ValidationGroup;
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
public class Schedule implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 日程id
     */
    @TableId(value = "schedule_serial", type = IdType.AUTO)
    @NotNull(groups = addKeyGroup.class, message = "请指明scheduleId")
    private Long scheduleSerial;

    /**
     * 事件编号
     */
    @NotNull(groups = updateGroup.class, message = "请输入信息，不能全部为空")
    private String eventCode;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

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

}
