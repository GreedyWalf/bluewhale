package com.qs.bluewhale.controller;

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


@Controller
@RequestMapping(value = "/user")
public class UserInfoController extends BaseController {

    @Resource
    private UserService userService;


    @RequestMapping(value = "/userInfo")
    public String userInfo(Model model, HttpServletRequest request) {
        if (BooleanUtils.isFalse(checkIsPjaxRequest(request))) {
            return "forward:/index";
        }

        String userId = ExecutionContext.getUserId();
        User user = userService.getUserByUserId(userId);
        model.addAttribute("user", user);
        return "user/userInfo";
    }


    @RequestMapping(value = "/updateUser")
    @ResponseBody
    public JsonResult updateUser(User user) {
        JsonResult jsonResult = new JsonResult();
        userService.updateUser(user);
        return jsonResult;
    }
}
