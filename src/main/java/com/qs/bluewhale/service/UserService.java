package com.qs.bluewhale.service;

import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.entity.User;

import java.util.List;

public interface UserService {

    void saveEntity(User user);

    User getUserByUserId(String userId);

    void delete(String userId);

    User getUserByUserName(String userName);

    Boolean existUserName(String userName);

    void updateUser(User user);

    PageInfo<User> getUserList(int currentNum, int limit, String keyword);

}
