package com.trainingmanagesys.web.recruit.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
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
 * @since 2020-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RecruiteeVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 招聘会编号
     */
    private String recruitCode;


    /**
     * 类型（学生、职员、教师）
     */
    private String catagory;

    /**
     * 审核编号
     */
    private Long auditSerial;

    private Integer limit;

    @NotNull(groups = listKeyGroup.class, message = "请指明当前页面")
    private Integer currentPage;

    @NotNull(groups = listKeyGroup.class, message = "请指明页面大小")
    private Integer pageSize;
}
