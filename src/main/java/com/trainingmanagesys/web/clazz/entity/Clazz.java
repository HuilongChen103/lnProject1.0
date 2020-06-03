package com.trainingmanagesys.web.clazz.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trainingmanagesys.utils.ValidationGroup;
import com.trainingmanagesys.web.clazz.validator.UpdateClazzValidator;
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
 * @since 2020-05-19
 */
// 启用lombok，自动生成getter、setter、constructor
@Data
// 相当于@Data相当于@Getter @Setter @RequiredArgsConstructor @ToString
// callSuper = false避免其调用父类的方法
@EqualsAndHashCode(callSuper = false)
// @Accessors用于配置getter和setter方法的生成结果,chain的中文含义是链式的，
// 设置为true，则setter方法返回当前对象
@Accessors(chain = true)
// 声明表的名字，让该类映射到表上
@TableName("t_class")
// 声明注解的验证类
@GroupSequenceProvider(UpdateClazzValidator.class)
// Serializable是序列化，序列化是将对象状态转换为可保持或传输的格式的过程。
// 与序列化相对的是反序列化，它将流转换为对象。这两个过程结合起来，可以轻松地存储和传输数据
// ValidationGroup是我自己定义的校验组，通过实现该接口从而避免自己多次重复写逻辑校验接口
public class Clazz implements Serializable, ValidationGroup {

    // 声明序列化id
    //serialVersionUID适用于java序列化机制。简单来说，JAVA序列化的机制是通过判断类的serialVersionUID
    // 来验证的版本一致的。在进行反序列化时，JVM会把传来的字节流中的serialVersionUID于本地相应实体类
    // 的serialVersionUID进行比较。如果相同说明是一致的，可以进行反序列化，否则会出现反序列化版本一致的异常，即是InvalidCastException。
    private static final long serialVersionUID = 1L;

    /**
     * 班级号
     */
    // 声明表的id
    @TableId(value = "class_code")
    // 指明该注解只有在指定group存在时才回去判断跟随的对象classCode是否为空，若为空
    // 则返回message中的信息
    @NotNull(groups = addKeyGroup.class, message = "请指明班级号")
    private String classCode;

    /**
     * 课程号
     */
    @NotNull(groups = updateGroup.class, message = "请输入信息，不能全部为空")
    private String courseCode;

    /**
     * 课程名称
     */
    // 这里的exists = false声明跟随的属性courseName不是表里面的属性
    @TableField(exist = false)
    private String courseName;

    /**
     * 起始学生数量(开学的时候)
     */
    private Integer studentNum;

    /**
     * 实际学生数量
     */
    private Integer realNum;

    /**
     * 最大学生数量
     */
    private Integer studentMax;

    /**
     * 教师id
     */
    private Long teacherId;

    /**
     * 教师名字
     */
    @TableField(exist = false)
    private String name;

    /**
     * 日程安排编号
     */
    private Long scheduleSerial;

    private Integer enable;

    private Long roomNum;
}
