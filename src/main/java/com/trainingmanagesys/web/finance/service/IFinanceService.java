package com.trainingmanagesys.web.finance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.finance.entity.Finance;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.finance.vo.FinanceVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-12
 */
public interface IFinanceService extends IService<Finance> {

    String addFinance(Finance finance);

    String updateFinance(Finance finance);

    Finance getFinance(String financeCode);

    String deleteFinance(String financeCode);

    List<Finance> listFinance(FinanceVO finance);

    IPage<Finance> pagedListFinance(FinanceVO finance);
}
