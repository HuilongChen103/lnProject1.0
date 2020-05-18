package com.trainingmanagesys.web.student.vo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2020-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StucourseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 课程编号
     */
    private String courseCode;

    /**
     * 班级编号
     */
    private String classCode;

    /**
     * 费用
     */
    private Long feeMax;

    private Long feeMin;

    /**
     * 是否支付(已支付，未支付)
     */
    private String pay;

    /**
     * 收支编号
     */
    private String financeCode;

    private Integer limit;

    @NotNull(groups = pageListGroup.class, message = "请指明当前页面")
    private Integer currentPage;

    @NotNull(groups = pageListGroup.class, message = "请指明当前页面大小")
    private Integer pageSize;


    public interface pageListGroup{

    }
}
