package com.nm.cost.service.system;

import com.nm.cost.model.Menu;
import com.nm.cost.model.User;

import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/2/23 0023 14:05
 */
public interface IUserService {

    //根据账号和密码查询用户信息
    User verifyUser(User user);

    User queryUser(User user);

    //根据roleId查询所拥有的菜单列表
    List<Menu> queryMenus(Integer roleId);

    //添加用户
    boolean addUser(User user);

    //超级用户条件查询用户
    List<User> findUserByUserAccountOrUserName(User user);

    //添加记录
    boolean updateUser(User user);

    //ajax检测账户是否存在
    int checkAccount(String userAccount);

    //假删除
    void delteUsers(Integer[] ids);

    List<User> queryUsers(User user);

    boolean register(User user);
}
