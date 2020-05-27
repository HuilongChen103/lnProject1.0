package com.trainingmanagesys.web.recruit.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.recruit.validator.UpdateRecruitValidator;
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
 * @since 2020-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RecruitVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;


    /**
     * 主办人id
     */
    @TableField("PIC_id")
    @NotNull(groups = updateGroup.class, message = "请输入信息，不能全部为空")
    private Long picId;

    /**
     * 日期安排
     */
    private Long scheduleSerial;

    /**
     * 地点
     */
    private String place;

    /**
     * 方式（网络，实地等）
     */
    private String method;

    /**
     * 招聘对象类型（教师，学生，职工）
     */
    private String catagory;

    private Integer limit;

    @NotNull(groups = listKeyGroup.class, message = "请指明当前页面")
    private Integer currentPage;

    @NotNull(groups = listKeyGroup.class, message = "请指明页面大小")
    private Integer pageSize;

}
