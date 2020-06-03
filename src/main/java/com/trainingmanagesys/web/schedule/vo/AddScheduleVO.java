package com.trainingmanagesys.web.schedule.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.schedule.validator.AddScheduleValidator;
import com.trainingmanagesys.web.schedule.validator.ListScheduleValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

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
@GroupSequenceProvider(AddScheduleValidator.class)
public class AddScheduleVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 事件编号
     */
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
    private String week;

    /**
     * 年份
     */
    @NotNull(groups = yearNotNullGroup.class, message = "请指明年份")
    private Integer year;

    /**
     * 季度
     */
    @NotNull(groups = semesterNotNullGroup.class, message = "请指明季度")
    private String semester;

    private Integer limit;

    public interface semesterNotNullGroup{

    }

}
