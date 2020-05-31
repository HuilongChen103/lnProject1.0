package com.trainingmanagesys.web.salary.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.salary.entity.Salary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.salary.vo.AddSalaryVO;
import com.trainingmanagesys.web.salary.vo.ListSalaryVO;
import com.trainingmanagesys.web.salary.vo.SalaryVO;
import com.trainingmanagesys.web.stuff.entity.Stuff;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-21
 */
public interface ISalaryService extends IService<Salary> {

    Long addSalary(AddSalaryVO vo);

    String updateSalary(Salary salary);

    String deleteSalary(Long salarySerial);

    Salary getSalary(Long salarySerial);

    List<Salary> listSalary(ListSalaryVO vo);

    IPage<Salary> pagedListSalary(SalaryVO vo);

    List<Stuff> listStuffByYearAndMonth(Integer year, Integer month);
}
