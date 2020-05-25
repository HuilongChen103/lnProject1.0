package com.trainingmanagesys.web.schedule.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.schedule.validator.ListScheduleValidator;
import com.trainingmanagesys.web.schedule.validator.UpdateScheduleValidator;
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
@GroupSequenceProvider(ListScheduleValidator.class)
public class ListScheduleVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 事件编号
     */
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
