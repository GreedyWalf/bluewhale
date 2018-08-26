package com.qs.bluewhale.controller;

import com.qs.bluewhale.base.BaseController;
import com.qs.bluewhale.entity.Menu;
import com.qs.bluewhale.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {

    @Resource
    private MenuService menuService;

    @RequestMapping(value = "/getMenu")
    @ResponseBody
    public Map<String, String> getMenu(String menuId) {
        return menuService.getMenuIdAndUrlMap(menuId);
    }

    @RequestMapping(value = "/getMenuList")
    @ResponseBody
    public List<Menu> getMenuList(String parentMenuId) {
        return menuService.getMenuListByParentMenuId(parentMenuId);
    }
}
