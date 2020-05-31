package com.trainingmanagesys.web.salary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.salary.entity.Salary;
import com.trainingmanagesys.web.salary.dao.SalaryMapper;
import com.trainingmanagesys.web.salary.service.ISalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.salary.vo.AddSalaryVO;
import com.trainingmanagesys.web.salary.vo.ListSalaryVO;
import com.trainingmanagesys.web.salary.vo.SalaryVO;
import com.trainingmanagesys.web.stuff.entity.Stuff;
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
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {

    private Salary checkSalaryExistence(Long salarySerial){
        Salary temp = getSalary(salarySerial);
        if (temp == null)
            throw new APIException("该工资流水不存在");
        return temp;
    }

    @Override
    public Long addSalary(AddSalaryVO vo) {
        Salary salary = new Salary();
        salary.setStuffId(vo.getStuffId());
        salary.setBasicSalary(vo.getBasicSalary());
        salary.setBonus(vo.getBonus());
        salary.setTotalSalary(vo.getTotalSalary());
        salary.setInsurance(vo.getInsurance());
        if (vo.getYear() != null){
            salary.setYear(vo.getYear());
            salary.setMonth(vo.getMonth());
        }
        baseMapper.insert(salary);
        return salary.getSalarySerial();
    }

    @Override
    public String updateSalary(Salary salary) {
        Salary temp = checkSalaryExistence(salary.getSalarySerial());
        // 存在以下情况：
        // 工资流水的年份不存在，但是要更新一个月份给它，此时应该报错
        if (temp.getYear() == null && salary.getMonth() != null)
            throw new APIException("该工资流水未设置年份，请设置年份");
        String result = "更新工资流水失败";
        int code = baseMapper.updateById(salary);
        if (code == 1)
            result = "更新工资流水成功";
        return result;
    }

    @Override
    public String deleteSalary(Long salarySerial) {
        checkSalaryExistence(salarySerial);
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

    @Override
    public List<Salary> listSalary(ListSalaryVO vo) {
        QueryWrapper<Salary> queryWrapper = new QueryWrapper<>();
        if (vo.getStuffId() != null) queryWrapper.eq("stuff_id", vo.getStuffId());
        if (vo.getYear() != null){
            queryWrapper.eq("year", vo.getYear());
            if (vo.getMonth() != null) queryWrapper.eq("month", vo.getMonth());
        }
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Salary> pagedListSalary(SalaryVO vo) {
        QueryWrapper<Salary> queryWrapper = new QueryWrapper<>();
        if (vo.getStuffId() != null) queryWrapper.eq("stuff_id", vo.getStuffId());
        if (vo.getYear() != null){
            queryWrapper.eq("year", vo.getYear());
            if (vo.getMonth() != null) queryWrapper.eq("month", vo.getMonth());
        }
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());

        Page<Salary> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getPageSize());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<Stuff> listStuffByYearAndMonth(Integer year, Integer month) {
        return null;
    }
}
