package com.qs.bluewhale.service.repository;

import com.qs.bluewhale.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUserId(String userId);

    User findUserByUserName(String userName);

}
