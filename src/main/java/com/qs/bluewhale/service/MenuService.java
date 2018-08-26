package com.qs.bluewhale.service;

import com.qs.bluewhale.entity.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {

    Menu getMenuByMenuId(String menuId);

    /**
     * 根据父菜单id，获取所有层级的菜单
     *
     * @param parentMenuId 父菜单id
     * @return 获取所有层级的菜单
     * @aythor qs
     * @since 2018年08月26日21:40:09
     */
    List<Menu> getMenuListByParentMenuId(String parentMenuId);

    /**
     * 获取key=menuId,value=url的map集合
     *
     * @param menuId 菜单id
     * @return 返回菜单对应的url映射集合
     * @author qs
     * @since 2018年08月26日21:39:57
     */
    Map<String, String> getMenuIdAndUrlMap(String menuId);
}
