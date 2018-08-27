package com.qs.bluewhale.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.service.UserService;
import com.qs.bluewhale.service.mapper.UserMapper;
import com.qs.bluewhale.service.repository.UserRepository;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public PageInfo<User> getUserList(int currentNum, int limit, String keyword) {
        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("start", currentNum);
        conditionMap.put("limit", limit);
        conditionMap.put("keyword", keyword);
        List<User> userList = userMapper.getUserList(conditionMap);
        PageHelper.startPage(currentNum, limit);
        return new PageInfo<>(userList);
    }

}
