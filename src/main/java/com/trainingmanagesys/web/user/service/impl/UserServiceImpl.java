package com.trainingmanagesys.web.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.utils.BaseConst;
import com.trainingmanagesys.web.user.entity.User;
import com.trainingmanagesys.web.user.dao.UserMapper;
import com.trainingmanagesys.web.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-04-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public String addUser(User user) {
        String result = "添加用户失败";
        int code = baseMapper.insert(user);
        if (code == 1)
            result = "添加用户成功";
        return result;
    }

    @Override
    public User updateUser(User user) {
        baseMapper.updateById(user);
        Long uid = user.getUid();
        User returnUser = getUser(uid, BaseConst.DATA_ENABLE);
        return returnUser;
    }

    @Override
    public User getUser(Long uid, Integer enable) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public String deleteUser(Long uid) {
        String result = "删除失败";
        User tempUser = getUser(uid, BaseConst.DATA_ENABLE);
        if (tempUser.getEnable() != 1){
            result = "用户不存在";
            return result;
        }else {
            tempUser.setEnable(BaseConst.DATA_DISABLE);
            updateUser(tempUser);
            result = "删除成功";
        }
        return result;
    }

    @Override
    public String getPosition(Long uid) {
        String position = baseMapper.getPosition(uid);
        return position;
    }

    @Override
    public List<User> listUser(User user) {
        String position = user.getPosition();
        String state = user.getState();
        Integer enable = user.getEnable();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if (position != null) queryWrapper.eq("position", position);
        if (state != null) queryWrapper.eq("state", state);
        if (enable != null) queryWrapper.eq("enable", enable);

        List<User> list = baseMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public IPage<User> pagedListUser(User user, Integer currentPage, Integer pageSize) {
        String position = user.getPosition();
        String state = user.getState();
        Integer enable = user.getEnable();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if (position != null) queryWrapper.eq("position", position);
        if (state != null) queryWrapper.eq("state", state);
        if (enable != null) queryWrapper.eq("enable", enable);

        Page<User> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        IPage<User> pagedList = baseMapper.selectPage(page, queryWrapper);
        return pagedList;
    }

    @Override
    public String logIn(Long uid, String password) {
        User user = getUser(uid, BaseConst.DATA_ENABLE);

        if (user == null){
            return "该用户不存在";
        }
        if (user.getPassword().compareTo(password) != 0){
            return "密码错误";
        }
        user.setState("online");
        updateUser(user);
        return "登陆成功";
    }

    @Override
    public String logOut(Long uid) {
        User user = getUser(uid, BaseConst.DATA_ENABLE);

        if (user == null){
            return "该用户不存在";
        }
        user.setState("offline");
        updateUser(user);
        return "登出成功";
    }


}
