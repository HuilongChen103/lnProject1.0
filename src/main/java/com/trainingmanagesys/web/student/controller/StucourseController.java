package com.trainingmanagesys.web.student.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.student.entity.Stucourse;
import com.trainingmanagesys.web.student.service.IStucourseService;
import com.trainingmanagesys.web.student.vo.StucourseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoying
 * @since 2020-05-13
 */
@Api(value = "学生课程", tags = {"学生课程操作接口"})
@RestController
@RequestMapping("/stucourse")
public class StucourseController {

    @Autowired
    IStucourseService stucourseService;

    @ApiOperation(value = "添加学生课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentId", value = "学生id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "courseCode", value = "课程编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "classCode", value = "班级编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "fee", value = "费用", dataType = "Long", required = false),
            @ApiImplicitParam(name = "pay", value = "是否支付(已支付，未支付)", dataType = "String", required = false),
            @ApiImplicitParam(name = "financeCode", value = "收支编号", dataType = "String", required = false)
    })
    @PostMapping("/addStucourse")
    public String addStucourse(@RequestBody @Validated Stucourse stucourse){
        return stucourseService.addStuCourse(stucourse);
    }

    @ApiOperation(value = "更新学生课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scSerial", value = "学生课程流水号", dataType = "Long", required = true),
            @ApiImplicitParam(name = "studentId", value = "学生id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "courseCode", value = "课程编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "classCode", value = "班级编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "fee", value = "费用", dataType = "Long", required = false),
            @ApiImplicitParam(name = "pay", value = "是否支付(已支付，未支付)", dataType = "String", required = false),
            @ApiImplicitParam(name = "financeCode", value = "收支编号", dataType = "String", required = false)
    })
    @PostMapping("/updateStucourse")
    public String updateStucourse(@RequestBody @Validated Stucourse stucourse){
        return stucourseService.updateStuCourse(stucourse);
    }

    @ApiOperation(value = "删除学生课程")
    @ApiImplicitParam(name = "scSerial", value = "学生课程流水号", dataType = "Long", required = true)
    @DeleteMapping("/deleteStucourse")
    public String deleteStucourse(@NotNull(message = "请指明学生课程流水号") Long scSerial){
        return stucourseService.deleteStuCourse(scSerial);
    }

    @ApiOperation(value = "列学生课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scSerial", value = "学生课程流水号", dataType = "Long", required = false),
            @ApiImplicitParam(name = "studentId", value = "学生id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "courseCode", value = "课程编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "classCode", value = "班级编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "fee", value = "费用", dataType = "Long", required = false),
            @ApiImplicitParam(name = "pay", value = "是否支付(已支付，未支付)", dataType = "String", required = false),
            @ApiImplicitParam(name = "financeCode", value = "收支编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false)
    })
    @PostMapping("/listStucourse")
    public List<Stucourse> listStucourse(@RequestBody @Validated StucourseVO stucourseVO){
        return stucourseService.listStuCourse(stucourseVO);
    }

    @ApiOperation(value = "分页列学生课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scSerial", value = "学生课程流水号", dataType = "Long", required = false),
            @ApiImplicitParam(name = "studentId", value = "学生id", dataType = "Long", required = false),
            @ApiImplicitParam(name = "courseCode", value = "课程编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "classCode", value = "班级编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "fee", value = "费用", dataType = "Long", required = false),
            @ApiImplicitParam(name = "pay", value = "是否支付(已支付，未支付)", dataType = "String", required = false),
            @ApiImplicitParam(name = "financeCode", value = "收支编号", dataType = "String", required = false),
            @ApiImplicitParam(name = "limit", value = "数量", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "currentPage", value = "数量", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageSize", value = "数量", dataType = "Integer", required = true)
    })
    @PostMapping("/listStucourse")
    public IPage<Stucourse> pagedListStucourse(@RequestBody @Validated(StucourseVO.pageListGroup.class) StucourseVO stucourseVO){
        return stucourseService.pagedListStuCourse(stucourseVO);
    }
}
