package com.qs.bluewhale.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.base.BaseController;
import com.qs.bluewhale.base.context.ExecutionContext;
import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.service.UserService;
import com.qs.bluewhale.utils.JsonResult;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;


    /**
     * 个人信息
     */
    @RequestMapping(value = "/userInfo")
    public String userInfo(Model model, HttpServletRequest request) {
        if (BooleanUtils.isFalse(checkIsPjaxRequest(request))) {
            request.setAttribute("isDispatcher", true);
            return "forward:/index";
        }

        String userId = ExecutionContext.getUserId();
        User user = userService.getUserByUserId(userId);
        model.addAttribute("user", user);
        return "user/userInfo";
    }


    /**
     * 用户列表
     */
    @RequestMapping(value = "/userList")
    public String userList(HttpServletRequest request, Model model) {
        if (BooleanUtils.isFalse(checkIsPjaxRequest(request))) {
            request.setAttribute("isDispatcher", true);
            return "forward:/index";
        }

        return "user/userList";
    }

    @RequestMapping(value = "/getUserList")
    @ResponseBody
    public Map<String, Object> getUserList(Page<User> page) {
        Page<User> userPage = userService.getUserList(page);
        PageInfo<User> pageInfo = new PageInfo<>(userPage);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("count", pageInfo.getTotal());
        dataMap.put("data", pageInfo.getList());
        dataMap.put("code", 0);
        return dataMap;
    }

    @RequestMapping(value = "/updateUser")
    @ResponseBody
    public JsonResult updateUser(User user) {
        JsonResult jsonResult = new JsonResult();
        userService.updateUser(user);
        return jsonResult;
    }

    @RequestMapping(value = "/addUser")
    public String addUser(){
        return "user/addUser";
    }
}
