package com.trainingmanagesys.web.user.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.utils.BaseConst;
import com.trainingmanagesys.web.user.entity.User;
import com.trainingmanagesys.web.user.service.IUserService;
import com.trainingmanagesys.web.user.vo.AddUserVO;
import com.trainingmanagesys.web.user.vo.LoginVO;
import com.trainingmanagesys.web.user.vo.PagedListUserVO;
import com.trainingmanagesys.web.user.vo.UpdateUserVO;
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
 * @since 2020-04-29
 */
@Api(value = "用户", tags = {"用户操作接口"})
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名字", dataType = "String", required = true),
            @ApiImplicitParam(name = "password", value = "用户密码", dataType = "String", required = true),
            @ApiImplicitParam(name = "gender", value = "性别", dataType = "String", required = false),
            @ApiImplicitParam(name = "birthday", value = "生日", dataType = "Date", required = false),
            @ApiImplicitParam(name = "position", value = "身份", dataType = "String", required = false),
            @ApiImplicitParam(name = "state", value = "登陆状态", dataType = "String", required = false),
            @ApiImplicitParam(name = "tel", value = "电话号码", dataType = "String", required = false)
    })
    @PostMapping("/addUser")
    public String addUser(@RequestBody @Validated User user){
        return userService.addUser(user);
    }

    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id", dataType = "String", required = true),
            @ApiImplicitParam(name = "name", value = "用户名字", dataType = "String", required = false),
            @ApiImplicitParam(name = "gender", value = "用户性别", dataType = "String", required = false),
            @ApiImplicitParam(name = "birthday", value = "用户生日", dataType = "Date", required = false),
            @ApiImplicitParam(name = "position", value = "用户身份", dataType = "String", required = false),
            @ApiImplicitParam(name = "password", value = "用户密码", dataType = "String", required = false)
    })
    @PostMapping("/updateUser")
    public User updateUser(@RequestBody @Validated UpdateUserVO vo){
        User user = UpdateUserVO.notAllNullUserVO2User(vo);
        return userService.updateUser(user);
    }

    @ApiOperation(value = "根据id获取用户", notes = "根据id获取用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id", dataType = "String", required = true)
    })
    @GetMapping("/getUserById")
    public User getUserById(@NotNull(message = "uid不能为空") Long uid){
        return userService.getUser(uid, BaseConst.DATA_ENABLE);
    }

    @ApiOperation(value = "根据id获取用户身份", notes = "根据id获取用户身份")
    @ApiImplicitParam(name = "uid", value = "用户id", dataType = "String", required = true)
    @GetMapping("/getUserPositionById")
    public String getUserPositionById(@NotNull(message = "uid不能为空") Long uid){
        return userService.getPosition(uid);
    }

    @ApiOperation(value = "根据id删除用户", notes = "根据id删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id", dataType = "String", required = true)
    })
    @DeleteMapping("/deleteUserById")
    public String deleteUserById(@NotNull(message = "uid不能为空") Long uid){
        return userService.deleteUser(uid);
    }

    @ApiOperation(value = "根据职位、登陆状态列用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "position", value = "用户职位", dataType = "String", required = false),
            @ApiImplicitParam(name = "state", value = "用户登录状态", dataType = "String", required = false),
            @ApiImplicitParam(name = "enable", value = "账号是否可用", dataType = "Integer", required = false)
    })
    @PostMapping("/listUser")
    public List<User> listUser(@RequestBody User user){
        return userService.listUser(user);
    }

    @ApiOperation(value = "根据职位、登陆状态分页列用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "position", value = "用户职位", dataType = "String", required = false),
            @ApiImplicitParam(name = "state", value = "用户登录状态", dataType = "String", required = false),
            @ApiImplicitParam(name = "enable", value = "账号是否可用", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "currentPage", value = "当前页面", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", dataType = "Integer", required = true),
    })
    @PostMapping("/pagedListUser")
    public IPage<User> pagedListUser(@RequestBody @Validated PagedListUserVO vo){
        Integer currentPage = vo.getCurrentPage();
        Integer pageSize = vo.getPageSize();
        User user = new User();
        user.setPosition(vo.getPosition());
        user.setState(vo.getState());
        user.setEnable(vo.getEnable());
        return userService.pagedListUser(user, currentPage, pageSize);
    }

    @ApiOperation(value = "输入uid以及密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户uid", dataType = "Long", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", required = true)
    })
    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginVO vo){

        Long uid = vo.getUid();
        String password = vo.getPassword();

        System.out.println("uid = " + uid);
        System.out.println("password = " + password);

        String result = userService.logIn(uid, password);
        if (result == "该用户不存在" || result == "密码错误"){
            APIException apiException = new APIException(1003, result);
            throw apiException;
        }
        return result;
    }

    @ApiOperation(value = "登出当前帐号")
    @ApiImplicitParam(name = "uid", value = "用户uid", dataType = "Long", required = true)
    @PostMapping("/logout")
    public String logout(@NotNull(message = "uid不能为空") Long uid){
        String result = userService.logOut(uid);
        if (result == "该用户不存在"){
            APIException apiException = new APIException(1003, result);
            throw apiException;
        }
        return result;
    }
}
