package com.trainingmanagesys.web.department.service;

import com.trainingmanagesys.web.department.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-21
 */
public interface IDepartmentService extends IService<Department> {

    Long addDepartment(Department department);

    String updateDepartment(Department department);

    String deleteDepartment(Long departmentId);

    Department getDepartment(Long departmentId);
}
