package com.trainingmanagesys.web.department.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.department.entity.Department;
import com.trainingmanagesys.web.department.dao.DepartmentMapper;
import com.trainingmanagesys.web.department.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    private Department checkDepartmentExistence(Long departmentId){
        Department tempDepartment = getDepartment(departmentId);
        if (tempDepartment == null)
            throw new APIException("该部门不存在");
        return tempDepartment;
    }

    @Override
    public Long addDepartment(Department department) {
        baseMapper.insert(department);
        return department.getDepartmentId();
    }

    @Override
    public String updateDepartment(Department department) {
        checkDepartmentExistence(department.getDepartmentId());
        String result = "更新部门失败";
        int code = baseMapper.updateById(department);
        if (code == 1)
            result = "更新部门成功";
        return result;
    }

    @Override
    public String deleteDepartment(Long departmentId) {
        checkDepartmentExistence(departmentId);
        String result = "删除部门失败";
        int code = baseMapper.deleteById(departmentId);
        if (code == 1)
            result = "删除部门成功";
        return result;
    }

    @Override
    public Department getDepartment(Long departmentId) {
        return baseMapper.selectById(departmentId);
    }

    @Override
    public List<Department> listDepartment() {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }
}
