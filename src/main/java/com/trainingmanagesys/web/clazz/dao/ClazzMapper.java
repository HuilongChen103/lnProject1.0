package com.trainingmanagesys.web.clazz.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.web.clazz.entity.Clazz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trainingmanagesys.web.clazz.vo.ClazzVO;
import com.trainingmanagesys.web.clazz.vo.ReturnedListClazzVO;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
public interface ClazzMapper extends BaseMapper<Clazz> {

    Clazz getClazz(@Param("classCode") String classCode, @Param("enable") Integer enable);

    List<ReturnedListClazzVO> listClazz(ClazzVO vo);

    List<ReturnedListClazzVO> pagedListClazz(Page<ReturnedListClazzVO> page, @Param("vo") ClazzVO vo);
}
