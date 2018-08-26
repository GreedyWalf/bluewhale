package com.qs.bluewhale.service;

import com.qs.bluewhale.entity.Menu;

import java.util.List;

public interface MenuService {

    Menu getMenuByMenuId(String menuId);

    /**
     * 根据父菜单id，获取所有层级的菜单
     *
     * @param parentMenuId 父菜单id
     * @return 获取所有层级的菜单
     */
    List<Menu> getMenuListByParentMenuId(String parentMenuId);
}
