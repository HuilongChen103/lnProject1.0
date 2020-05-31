package com.trainingmanagesys.web.stuff.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.stuff.entity.Stuff;
import com.trainingmanagesys.web.stuff.dao.StuffMapper;
import com.trainingmanagesys.web.stuff.service.IStuffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.stuff.vo.ReturnedSpecialListStuffVO;
import com.trainingmanagesys.web.stuff.vo.StuffVO;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private void checkStuffExistence(Long stuffId){
        if (getStuff(stuffId) == null)
            throw new APIException("该职工不存在");
    }

    @Override
    public Long addStuff(Stuff stuff) {
        baseMapper.insert(stuff);
        return stuff.getStuffId();
    }

    @Override
    public String updateStuff(Stuff stuff) {
        checkStuffExistence(stuff.getStuffId());
        String result = "更新职工失败";
        int code = baseMapper.updateById(stuff);
        if (code == 1)
            result = "更新职工成功";
        return result;
    }

    @Override
    public String deleteStuff(Long stuffId) {
        checkStuffExistence(stuffId);
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

    @Override
    public List<Stuff> listStuff(StuffVO vo) {
        QueryWrapper<Stuff> queryWrapper = new QueryWrapper<>();
        if (vo.getDepartmentId() != null) queryWrapper.eq("department_id", vo.getDepartmentId());
        if (vo.getPosition() != null) queryWrapper.eq("position", vo.getPosition());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Stuff> pagedListStuff(StuffVO vo) {
        QueryWrapper<Stuff> queryWrapper = new QueryWrapper<>();
        if (vo.getDepartmentId() != null) queryWrapper.eq("department_id", vo.getDepartmentId());
        if (vo.getPosition() != null) queryWrapper.eq("position", vo.getPosition());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());

        Page<Stuff> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getPageSize());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<ReturnedSpecialListStuffVO> listSpecializedStuff(Stuff stuff) {
        System.out.println(stuff);
        return baseMapper.listSpecializedStuff(stuff);
    }
}
