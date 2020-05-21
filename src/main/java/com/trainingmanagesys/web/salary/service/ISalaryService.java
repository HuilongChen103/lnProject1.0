package com.trainingmanagesys.web.salary.service;

import com.trainingmanagesys.web.salary.entity.Salary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-21
 */
public interface ISalaryService extends IService<Salary> {

    Long addSalary(Salary salary);

    String updateSalary(Salary salary);

    String deleteSalary(Long salarySerial);

    Salary getSalary(Long salarySerial);
}
