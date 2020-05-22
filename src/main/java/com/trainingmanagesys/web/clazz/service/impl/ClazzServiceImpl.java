package com.trainingmanagesys.web.clazz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.clazz.entity.Clazz;
import com.trainingmanagesys.web.clazz.dao.ClazzMapper;
import com.trainingmanagesys.web.clazz.service.IClazzService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.clazz.vo.ClazzVO;
import com.trainingmanagesys.web.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-19
 */
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements IClazzService {

    private Clazz checkClazzExistence(String classCode){
        Clazz tempClazz = getClazz(classCode);
        if (tempClazz == null){
            APIException apiException = new APIException("该班级不存在");
            throw apiException;
        }
        return tempClazz;
    }


    @Override
    public String addClazz(Clazz clazz) {
        if (getClazz(clazz.getClassCode()) != null){
            APIException apiException = new APIException("该班级已存在，请更换班级号");
            throw apiException;
        }
        baseMapper.insert(clazz);
        return clazz.getClassCode();
    }

    @Override
    public String updateClazz(Clazz clazz) {
        checkClazzExistence(clazz.getClassCode());
        String result = "更新班级失败";
        int code = baseMapper.updateById(clazz);
        if (code == 1)
            result = "更新班级成功";
        return result;
    }

    @Override
    public String deleteClazz(String classCode) {
        checkClazzExistence(classCode);
        String result = "删除班级失败";
        int code = baseMapper.deleteById(classCode);
        if (code == 1)
            result = "删除班级成功";
        return result;
    }

    @Override
    public Clazz getClazz(String classCode) {
        return baseMapper.selectById(classCode);
    }

    @Override
    public List<Clazz> listClazz(ClazzVO vo) {
        QueryWrapper<Clazz> queryWrapper = new QueryWrapper<>();

        if (vo.getCourseCode() != null) queryWrapper.eq("course_code", vo.getCourseCode());
        if (vo.getTeacherId() != null) queryWrapper.eq("teacher_id", vo.getTeacherId());
        if (vo.getClassNum() != null) queryWrapper.eq("class_num", vo.getClassNum());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());

        List<Clazz> list = baseMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public IPage<Clazz> pagedListClazz(ClazzVO vo) {
        QueryWrapper<Clazz> queryWrapper = new QueryWrapper<>();

        if (vo.getCourseCode() != null) queryWrapper.eq("course_code", vo.getCourseCode());
        if (vo.getTeacherId() != null) queryWrapper.eq("teacher_id", vo.getTeacherId());
        if (vo.getClassNum() != null) queryWrapper.eq("class_num", vo.getClassNum());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());

        Page<Clazz> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getPageSize());
        IPage<Clazz> pagedList = baseMapper.selectPage(page, queryWrapper);
        return pagedList;
    }
}
