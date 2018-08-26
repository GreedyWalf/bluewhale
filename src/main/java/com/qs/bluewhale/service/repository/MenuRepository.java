package com.qs.bluewhale.service.repository;

import com.qs.bluewhale.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, String> {

    Menu findMenuByMenuId(String menuId);
}
