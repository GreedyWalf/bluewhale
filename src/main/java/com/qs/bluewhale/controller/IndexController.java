package com.qs.bluewhale.controller;

import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.service.UserService;
import com.qs.bluewhale.utils.JsonResult;
import com.qs.bluewhale.utils.JsonStatus;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    @Resource
    private UserService userService;

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/ajaxLogin")
    @ResponseBody
    public JsonResult ajaxLogin(User user) {
        JsonResult jsonResult = new JsonResult();
        Map<String, String> resultMap = new HashMap<>();
        if (user == null) {
            jsonResult.setMsg("参数错误！");
            jsonResult.setStatus(JsonStatus.ERROR);
            return jsonResult;
        }

        String userName = user.getUserName();
        String password = user.getPassword();
        System.out.println("-->>md5加密后的密码：" + DigestUtils.md5Hex(password));
        UsernamePasswordToken token = new UsernamePasswordToken(userName, DigestUtils.md5Hex(password).toCharArray());
        Subject subject = SecurityUtils.getSubject();
        try {
            //调用login()方法会调用自定义领域中的登录认证方法：CustomRealm.doGetAuthenticationInfo()
            subject.login(token);
        } catch (UnknownAccountException e) {
            resultMap.put("url", "login");
            jsonResult = new JsonResult(JsonStatus.ERROR, "用户名不存在", resultMap);
            return jsonResult;
        } catch (IncorrectCredentialsException e) {
            resultMap.put("url", "login");
            jsonResult = new JsonResult(JsonStatus.ERROR, "密码不正确", resultMap);
            return jsonResult;
        } catch (Exception e) {
            resultMap.put("url", "login");
            jsonResult = new JsonResult(JsonStatus.ERROR, "登录异常", resultMap);
            return jsonResult;
        }

        resultMap.put("url", "index");
        jsonResult.setResultMap(resultMap);
        return jsonResult;
    }

    @RequestMapping(value = "/gallery")
    public String gallery() {
        return "gallery";
    }

    @RequestMapping(value = "/calendar")
    public String calendar() {
        return "calendar";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }


    @RequestMapping(value = "/getUser")
    @ResponseBody
    public User getUser(String userId) {
        return userService.getUserByUserId(userId);
    }

    @RequestMapping(value = "/saveUser")
    @ResponseBody
    public String saveUser(User user) {
        userService.saveEntity(user);
        return "添加用户成功！";
    }


    @RequestMapping(value = "/deleteUser")
    @ResponseBody
    private String deleteUser(String userId) {
        userService.delete(userId);
        return "删除用户成功！";
    }
}
