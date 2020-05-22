package com.trainingmanagesys.web.department.service.impl;

import com.trainingmanagesys.web.department.entity.Department;
import com.trainingmanagesys.web.department.dao.DepartmentMapper;
import com.trainingmanagesys.web.department.service.IDepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Override
    public Long addDepartment(Department department) {
        baseMapper.insert(department);
        return department.getDepartmentId();
    }

    @Override
    public String updateDepartment(Department department) {
        String result = "更新部门失败";
        int code = baseMapper.updateById(department);
        if (code == 1)
            result = "更新部门成功";
        return result;
    }

    @Override
    public String deleteDepartment(Long departmentId) {
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
}
