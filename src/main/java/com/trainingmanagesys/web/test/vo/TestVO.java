package com.trainingmanagesys.web.test.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class TestVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级号
     */
    private String classCode;

    /**
     * 监考人
     */
    private Long testerId;

    /**
     * 考试内容文件
     */
    private Long testFile;

    /**
     * 日程安排流水号
     */
    private Long scheduleSerial;

    private Integer limit;

    private Integer currentPage;

    private Integer pageSize;


    public interface basicNotNullGroup{

    }
}
