package com.trainingmanagesys.web.user.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-04-29
 */
public interface IUserService extends IService<User> {
    Long addUser(User user);

    User updateUser(User user);

    User getUser(Long uid, Integer enable);

    String deleteUser(Long uid);

    String getPosition(Long uid);

    List<User> listUser(User user);

    IPage<User> pagedListUser(User user, Integer currentPage, Integer  pageSize);

    String logIn(Long uid, String password);

    String logOut(Long uid);
}
