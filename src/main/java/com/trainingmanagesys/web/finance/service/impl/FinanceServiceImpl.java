package com.trainingmanagesys.web.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.web.finance.entity.Finance;
import com.trainingmanagesys.web.finance.dao.FinanceMapper;
import com.trainingmanagesys.web.finance.service.IFinanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.finance.vo.FinanceVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-12
 */
@Service
public class FinanceServiceImpl extends ServiceImpl<FinanceMapper, Finance> implements IFinanceService {

    @Override
    public String addFinance(Finance finance) {
        String result = "添加财务失败";
        int code = baseMapper.insertAll(finance);
        if (code == 1)
            result = "添加财务成功";
        return result;
    }

    @Override
    public String updateFinance(Finance finance) {
        String result = "更新财务失败";
        int code = baseMapper.updateById(finance);
        if (code == 1)
            result = "更新财务成功";
        return result;
    }

    @Override
    public Finance getFinance(String financeCode) {
        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("finance_code", financeCode);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public String deleteFinance(String financeCode) {
        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("finance_code", financeCode);
        String result = "删除财务记录失败";
        int code = baseMapper.delete(queryWrapper);
        if (code == 1)
            result = "删除财务记录成功";
        return result;
    }

    @Override
    public List<Finance> listFinance(FinanceVO finance) {
        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
        if (finance.getInOut() != null) queryWrapper.eq("in_out", finance.getInOut());
        if (finance.getPicId() != null) queryWrapper.eq("PIC_id", finance.getPicId());
        if (finance.getPayAccount() != null) queryWrapper.eq("pay_account", finance.getPayAccount());
        if (finance.getReceiveAccount() != null) queryWrapper.eq("receive_account", finance.getReceiveAccount());
        if (finance.getTradeMethod() != null) queryWrapper.eq("trade_method", finance.getTradeMethod());
        if (finance.getMinAmount() != null) queryWrapper.ge("amount", finance.getMinAmount());
        if (finance.getMaxAmount() != null) queryWrapper.le("amount", finance.getMaxAmount());
        if (finance.getDate() != null) queryWrapper.eq("date", finance.getDate());
        if (finance.getCategory() != null) queryWrapper.eq("category", finance.getCategory());
        if (finance.getLimit() != null) queryWrapper.last(" limit " + finance.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Finance> pagedListFinance(FinanceVO finance) {
        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
        if (finance.getInOut() != null) queryWrapper.eq("in_out", finance.getInOut());
        if (finance.getPicId() != null) queryWrapper.eq("PIC_id", finance.getPicId());
        if (finance.getPayAccount() != null) queryWrapper.eq("pay_account", finance.getPayAccount());
        if (finance.getReceiveAccount() != null) queryWrapper.eq("receive_account", finance.getReceiveAccount());
        if (finance.getTradeMethod() != null) queryWrapper.eq("trade_method", finance.getTradeMethod());
        if (finance.getMinAmount() != null) queryWrapper.ge("amount", finance.getMinAmount());
        if (finance.getMaxAmount() != null) queryWrapper.le("amount", finance.getMaxAmount());
        if (finance.getDate() != null) queryWrapper.eq("date", finance.getDate());
        if (finance.getCategory() != null) queryWrapper.eq("category", finance.getCategory());
        if (finance.getLimit() != null) queryWrapper.last(" limit " + finance.getLimit());

        Page<Finance> page = new Page<>();
        page.setCurrent(finance.getCurrentPage());
        page.setSize(finance.getPageSize());
        IPage<Finance> pagedList = baseMapper.selectPage(page, queryWrapper);
        return pagedList;
    }
}
