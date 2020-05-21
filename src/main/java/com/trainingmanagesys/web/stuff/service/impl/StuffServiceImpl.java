package com.trainingmanagesys.web.stuff.service.impl;

import com.trainingmanagesys.web.stuff.entity.Stuff;
import com.trainingmanagesys.web.stuff.dao.StuffMapper;
import com.trainingmanagesys.web.stuff.service.IStuffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-21
 */
@Service
public class StuffServiceImpl extends ServiceImpl<StuffMapper, Stuff> implements IStuffService {

    @Override
    public Long addStuff(Stuff stuff) {
        baseMapper.insert(stuff);
        return stuff.getStuffId();
    }

    @Override
    public String updateStuff(Stuff stuff) {
        String result = "更新职工失败";
        int code = baseMapper.updateById(stuff);
        if (code == 1)
            result = "更新职工成功";
        return result;
    }

    @Override
    public String deleteStuff(Long stuffId) {
        String result = "删除职工失败";
        int code = baseMapper.deleteById(stuffId);
        if (code == 1)
            result = "删除职工成功";
        return result;
    }

    @Override
    public Stuff getStuff(Long stuffId) {
        return baseMapper.selectById(stuffId);
    }
}
