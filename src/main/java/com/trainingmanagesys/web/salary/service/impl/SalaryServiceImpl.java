package com.trainingmanagesys.web.salary.service.impl;

import com.trainingmanagesys.web.salary.entity.Salary;
import com.trainingmanagesys.web.salary.dao.SalaryMapper;
import com.trainingmanagesys.web.salary.service.ISalaryService;
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
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {

    @Override
    public Long addSalary(Salary salary) {
        baseMapper.insert(salary);
        return salary.getSalarySerial();
    }

    @Override
    public String updateSalary(Salary salary) {
        String result = "更新工资流水失败";
        int code = baseMapper.updateById(salary);
        if (code == 1)
            result = "更新工资流水成功";
        return result;
    }

    @Override
    public String deleteSalary(Long salarySerial) {
        String result = "删除工资流水失败";
        int code = baseMapper.deleteById(salarySerial);
        if (code == 1)
            result = "删除工资流水成功";
        return result;
    }

    @Override
    public Salary getSalary(Long salarySerial) {
        return baseMapper.selectById(salarySerial);
    }
}
