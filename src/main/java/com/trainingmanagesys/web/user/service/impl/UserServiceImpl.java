package com.trainingmanagesys.web.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
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

    /**
     * 判断用户是否存在，不存在则抛出异常
     * @param uid
     */
    public User checkUserExistance(Long uid){
        User tempUser = getUser(uid, BaseConst.DATA_ENABLE);
        if (tempUser == null){
            if (getUser(uid, BaseConst.ACCOUNT_BANNED) != null){
                APIException apiException = new APIException("该用户已封禁");
                throw apiException;
            }
            if (getUser(uid, BaseConst.DATA_DISABLE) != null){
                APIException apiException = new APIException("该用户已被删除");
                throw apiException;
            }
            APIException apiException = new APIException("该用户不存在");
            throw apiException;

        }
        return tempUser;
    }

    @Override
    public Long addUser(User user) {
        String result = "添加用户失败";
        //return baseMapper.insertUserAndGetId(user);
        baseMapper.insert(user);
        return user.getUid();
    }

    @Override
    public String updateUser(User user) {
        checkUserExistance(user.getUid());
        baseMapper.updateById(user);
        return "更新用户成功";
    }

    @Override
    public User getUser(Long uid, Integer enable) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        if (enable != null) queryWrapper.eq("enable", enable);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public String deleteUser(Long uid) {
        String result = "删除失败";
        User tempUser = checkUserExistance(uid);
        tempUser.setEnable(BaseConst.DATA_DISABLE);
        updateUser(tempUser);
        result = "删除成功";
        return result;
    }

    @Override
    public String getPosition(Long uid) {
        checkUserExistance(uid);
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
        User user = checkUserExistance(uid);
        if (user.getPassword().compareTo(password) != 0){
            return "密码错误";
        }
        user.setState("online");
        updateUser(user);
        return "登陆成功";
    }

    @Override
    public String logOut(Long uid) {
        User user = checkUserExistance(uid);
        user.setState("offline");
        updateUser(user);
        return "登出成功";
    }

    @Override
    public String prohibitUser(Long uid) {
        User user = checkUserExistance(uid);
        user.setEnable(BaseConst.ACCOUNT_BANNED);
        updateUser(user);
        return "封禁操作成功";
    }

    @Override
    public String recoverUser(Long uid) {
        User user = getUser(uid, BaseConst.ACCOUNT_BANNED);
        if (user == null)
            throw new APIException("该账号未被封禁或不存在");
        user.setEnable(BaseConst.DATA_ENABLE);
        baseMapper.updateById(user);
        return "解封账号成功";
    }

    @Override
    public String cancelUser(Long uid) {
        User user = checkUserExistance(uid);
        user.setEnable(BaseConst.DATA_DISABLE);
        updateUser(user);
        return "注销账号成功";
    }


}
