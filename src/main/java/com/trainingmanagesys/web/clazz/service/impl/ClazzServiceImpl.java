package com.trainingmanagesys.web.clazz.service.impl;

import com.trainingmanagesys.web.clazz.entity.Clazz;
import com.trainingmanagesys.web.clazz.dao.ClazzMapper;
import com.trainingmanagesys.web.clazz.service.IClazzService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public String addClazz(Clazz clazz) {
        baseMapper.insert(clazz);
        return clazz.getClassCode();
    }

    @Override
    public String updateClazz(Clazz clazz) {
        String result = "更新班级失败";
        int code = baseMapper.updateById(clazz);
        if (code == 1)
            result = "更新班级成功";
        return result;
    }

    @Override
    public String deleteClazz(String classCode) {
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
}
