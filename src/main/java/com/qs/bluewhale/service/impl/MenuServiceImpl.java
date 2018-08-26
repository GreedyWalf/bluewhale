package com.qs.bluewhale.service.impl;

import com.qs.bluewhale.entity.Menu;
import com.qs.bluewhale.service.MenuService;
import com.qs.bluewhale.service.mapper.MenuMapper;
import com.qs.bluewhale.service.repository.MenuRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
        wrapMenuList(allMenuList, parentMenuId);
        return allMenuList;
    }


    private void wrapMenuList(List<Menu> allMenuList, String parentMenuId) {
        if (CollectionUtils.isEmpty(allMenuList)) {
            return;
        }

        for (Menu menu : allMenuList) {
            String menuId = menu.getMenuId();
            List<Menu> childMenus = menuMapper.getMenuListByParentMenuId(menuId);
            if (CollectionUtils.isEmpty(childMenus)) {
                continue;
            }

            menu.setChildrenMenus(childMenus);
            wrapMenuList(childMenus, menuId);
        }
    }
}
