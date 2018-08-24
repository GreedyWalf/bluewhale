package com.qs.bluewhale.controller;

import com.qs.bluewhale.context.ExecutionContext;
import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.service.UserService;
import com.qs.bluewhale.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping(value = "/user")
public class UserInfoController {

    @Resource
    private UserService userService;


    @RequestMapping(value = "/userInfo")
    public String userInfo(Model model) {
        String userId = ExecutionContext.getUserId();
        User user = userService.getUserByUserId(userId);
        model.addAttribute("user", user);
        return "user/userInfo";
    }


    @RequestMapping(value = "/updateUser")
    @ResponseBody
    public JsonResult updateUser(User user){
        JsonResult jsonResult = new JsonResult();
        userService.updateUser(user);
        return jsonResult;
    }
}
