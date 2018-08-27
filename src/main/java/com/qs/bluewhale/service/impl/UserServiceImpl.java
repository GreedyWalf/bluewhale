package com.qs.bluewhale.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.service.UserService;
import com.qs.bluewhale.service.mapper.UserMapper;
import com.qs.bluewhale.service.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserMapper userMapper;


    @Override
    public void saveEntity(User user) {
        userMapper.insert(user);
    }

    @Override
    public User getUserByUserId(String userId) {
        return userRepository.findUserByUserId(userId);
    }

    @Override
    public void delete(String userId) {
        userMapper.delete(userId);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public Boolean existUserName(String userName) {
        return userRepository.countByUserName(userName) > 0;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public Page<User> getUserList(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return userMapper.findByPage();
    }
}
