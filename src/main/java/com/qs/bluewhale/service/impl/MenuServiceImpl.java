package com.qs.bluewhale.service.impl;

import com.qs.bluewhale.entity.Menu;
import com.qs.bluewhale.service.MenuService;
import com.qs.bluewhale.service.mapper.MenuMapper;
import com.qs.bluewhale.service.repository.MenuRepository;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service(value = "menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuRepository menuRepository;

    @Resource
    private MenuMapper menuMapper;

    public Menu getMenuByMenuId(String menuId) {
        return menuRepository.findMenuByMenuId(menuId);
    }

    @Override
    public List<Menu> getMenuListByParentMenuId(String parentMenuId) {
        List<Menu> allMenuList = menuMapper.getMenuListByParentMenuId(parentMenuId);
        //获取所有菜单以及菜单之间的层级关系
        wrapMenuList(allMenuList, parentMenuId);
        return allMenuList;
    }

    private void wrapMenuList(List<Menu> allMenuList, String parentMenuId) {
        if (CollectionUtils.isEmpty(allMenuList)) {
            return;
        }

        for (Menu menu : allMenuList) {
            String menuId = menu.getMenuId();
            wrapMenuUrl(menu);
            List<Menu> childMenus = menuMapper.getMenuListByParentMenuId(menuId);
            if (CollectionUtils.isEmpty(childMenus)) {
                continue;
            }

            menu.setChildrenMenus(childMenus);
            wrapMenuList(childMenus, menuId);
        }
    }

    private void wrapMenuUrl(Menu menu) {
        if (menu == null) {
            return;
        }

        Map<String, String> menuIdAndUrlMap = getMenuIdAndUrlMap(null);
        String menuIdPath = menu.getMenuIdPath();
        String[] menuIdArr = menuIdPath.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (String menuId : menuIdArr) {
            String url = menuIdAndUrlMap.get(menuId);
            if (StringUtils.isBlank(url)) {
                url = "NULL";
            }

            sb.append(url).append(",");
        }

        String menuUrlPath = sb.toString();
        if(StringUtils.isNotBlank(menuUrlPath)){
            menuUrlPath = menuUrlPath.substring(0, menuUrlPath.lastIndexOf(","));
            menu.setMenuUrlPath(menuUrlPath);
        }
    }

    @Override
    public Map<String, String> getMenuIdAndUrlMap(String menuId) {
        Map<String, Menu> menuIdAndMenuMap = menuMapper.getMenuIdAndUrlMap(menuId);
        Map<String, String> menuIdAndUrlMap = new HashMap<>(menuIdAndMenuMap.size());
        for (Map.Entry<String, Menu> entry : menuIdAndMenuMap.entrySet()) {
            Menu menu = entry.getValue();
            menuIdAndUrlMap.put(entry.getKey(), menu.getUrl());
        }

        return menuIdAndUrlMap;
    }
}
