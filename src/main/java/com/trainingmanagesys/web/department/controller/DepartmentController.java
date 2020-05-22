package com.trainingmanagesys.web.department.controller;


import com.trainingmanagesys.web.department.entity.Department;
import com.trainingmanagesys.web.department.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoying
 * @since 2020-05-21
 */
@Api(value = "部门", tags = {"部门操作接口"})
@RestController
@RequestMapping("/department")
@Validated
public class DepartmentController {

    @Autowired
    IDepartmentService departmentService;

    @ApiOperation(value = "添加部门")
    @ApiImplicitParam(name = "name", value = "名字", dataType = "String", required = false)
    @PostMapping("/addDepartment")
    @Transactional(rollbackFor = Exception.class)
    public Long addDepartment(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }

    @ApiOperation(value = "修改部门")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "departmentId", value = "部门ID", dataType = "Long", required = true),
        @ApiImplicitParam(name = "name", value = "名字", dataType = "String", required = false),
    })
    @PostMapping("/updateDepartment")
    @Transactional(rollbackFor = Exception.class)
    public String updateDepartment(@RequestBody @Validated(Department.updateGroup.class) Department department){
        return departmentService.updateDepartment(department);
    }

    @ApiOperation(value = "删除部门")
    @ApiImplicitParam(name = "departmentId", value = "部门ID", dataType = "Long", required = true)
    @PostMapping("/deleteDepartment")
    @Transactional(rollbackFor = Exception.class)
    public String deleteDepartment(@NotNull(message = "请指明部门ID") Long departmentId){
        return departmentService.deleteDepartment(departmentId);
    }

    @ApiOperation(value = "获得部门")
    @ApiImplicitParam(name = "departmentId", value = "部门ID", dataType = "Long", required = true)
    @PostMapping("/getDepartment")
    public Department getDepartment(@NotNull(message = "请指明部门ID") Long departmentId){
        return departmentService.getDepartment(departmentId);
    }
}
