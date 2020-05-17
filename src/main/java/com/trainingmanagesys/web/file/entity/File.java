package com.trainingmanagesys.web.file.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainingmanagesys.web.file.validator.UpdateFileValidator;
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
 * @since 2020-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_file")
@GroupSequenceProvider(UpdateFileValidator.class)
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件流水号
     */
    @TableId(value = "file_serial", type = IdType.AUTO)
    @NotNull(groups = basicNotNullGroup.class, message = "请指明文件流水号")
    private Long fileSerial;

    /**
     * 文件名
     */
    @NotNull(groups = notAllNullGroup.class, message = "请输入信息，不能全部为空")
    private String name;

    /**
     * 文件展示名
     */
    private String displayname;

    /**
     * 文件扩展名
     */
    private String extension;

    /**
     * 文件种类
     */
    private String contenttype;

    /**
     * 文件二进制格式
     */
    private byte[] fileData;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 上传时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date uploadTime;

    /**
     * 上传人id
     */
    private Long uploaderId;

    public interface basicNotNullGroup{

    }

    public interface notAllNullGroup{

    }
}
