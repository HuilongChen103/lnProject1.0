package com.trainingmanagesys.web.homework.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.homework.validator.UpdateHomeworkValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.group.GroupSequenceProvider;

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
public class HomeworkVO implements Serializable, ValidationGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 作业安排流水号
     */
    private Long arrangeSerial;

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 作业文件
     */
    private Long hwFile;

    /**
     * 分数
     */
    private Long gradeMax;

    private Long gradeMin;

    private Integer limit;

    @NotNull(groups = listKeyGroup.class, message = "请指明当前页面")
    private Integer currentPage;

    @NotNull(groups = listKeyGroup.class, message = "请指明页面容量")
    private Integer pageSize;
}
