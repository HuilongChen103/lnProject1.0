package com.trainingmanagesys.web.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.utils.BaseConst;
import com.trainingmanagesys.web.user.entity.User;
import com.trainingmanagesys.web.user.dao.UserMapper;
import com.trainingmanagesys.web.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.user.vo.PagedListUserVO;
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
    public Long addUser(User user) {
        String result = "添加用户失败";
        //return baseMapper.insertUserAndGetId(user);
        baseMapper.insert(user);
        return user.getUid();
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
    public List<User> listUser(PagedListUserVO vo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if (vo.getPosition() != null) queryWrapper.eq("position", vo.getPosition());
        if (vo.getState() != null) queryWrapper.eq("state", vo.getState());
        if (vo.getEnable() != null) queryWrapper.eq("enable", vo.getEnable());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());

        List<User> list = baseMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public IPage<User> pagedListUser(PagedListUserVO vo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if (vo.getPosition() != null) queryWrapper.eq("position", vo.getPosition());
        if (vo.getEnable() != null) queryWrapper.eq("enable", vo.getEnable());
        if (vo.getState() != null) queryWrapper.eq("state", vo.getState());
        if (vo.getLimit() != null) queryWrapper.last(" limit " + vo.getLimit());

        Page<User> page = new Page<>();
        page.setCurrent(vo.getCurrentPage());
        page.setSize(vo.getPageSize());
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
