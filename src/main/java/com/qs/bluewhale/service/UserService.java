package com.qs.bluewhale.service;

import com.qs.bluewhale.entity.User;

public interface UserService {

    void saveEntity(User user);

    User getUserByUserId(String userId);

    void delete(String userId);

    User getUserByUserName(String userName);

    Boolean existUserName(String userName);
}
