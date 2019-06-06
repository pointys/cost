package com.nm.cost.service.system.impl;

import com.nm.cost.mapper.UserMapper;
import com.nm.cost.model.Menu;
import com.nm.cost.model.User;
import com.nm.cost.service.system.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/2/23 0023 14:05
 */
@Slf4j
@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(User user) {
        int i = userMapper.addUser(user);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> findUserByUserAccountOrUserName(User user) {
        return userMapper.queryUsers(user);
    }

    @Override
    public boolean updateUser(User user) {
        if (userMapper.updateUser(user) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int checkAccount(String userAccount) {
       return userMapper.checkAccount(userAccount);
    }

    @Override
    public void delteUsers(Integer[] ids) {
        userMapper.delteUsers(ids);
    }

    @Override
    public List<User> queryUsers(User user) {
        return userMapper.queryUsers(user);
    }

    @Override
    public boolean register(User user) {
        int i=userMapper.register(user);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public User verifyUser(User user) {
        return userMapper.verifyUser(user);
    }

    @Override
    public User queryUser(User user) {
        return userMapper.queryUser(user);
    }

    @Override
    public List<Menu> queryMenus(Integer roleId) {
        return userMapper.queryMenus(roleId);
    }


}
