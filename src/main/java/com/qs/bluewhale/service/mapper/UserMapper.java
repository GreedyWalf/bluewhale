package com.qs.bluewhale.service.mapper;

import com.github.pagehelper.Page;
import com.qs.bluewhale.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    void insert(User user);

    void delete(String userId);

    void updateUser(User user);

    List<User> getAllUsers();

    List<User> getUserList(int startNum, int pageSize);

    Page<User> findByPage();
}
