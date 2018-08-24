package com.qs.bluewhale.service.mapper;

import com.qs.bluewhale.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insert(User user);

    void delete(String userId);

    User findUserByUserId(String userId);
}
