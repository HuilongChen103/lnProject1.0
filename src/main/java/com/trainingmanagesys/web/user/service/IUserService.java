package com.trainingmanagesys.web.user.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.user.vo.PagedListUserVO;

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

    String updateUser(User user);

    User getUser(Long uid, Integer enable);

    String deleteUser(Long uid);

    String getPosition(Long uid);

    List<User> listUser(PagedListUserVO vo);

    IPage<User> pagedListUser(PagedListUserVO vo);

    String logIn(Long uid, String password);

    String logOut(Long uid);

    String prohibitUser(Long uid);
}
