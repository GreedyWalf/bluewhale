package com.qs.bluewhale.controller;

import com.qs.bluewhale.base.BaseController;
import com.qs.bluewhale.base.context.ExecutionContext;
import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.service.UserService;
import com.qs.bluewhale.utils.JsonResult;
import com.qs.bluewhale.utils.JsonStatus;
import com.qs.bluewhale.utils.UUIDGenerator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController extends BaseController {

    @Value("${shiro.password.salt}")
    private String salt;

    @Value("${shiro.password.hashIterations}")
    private Integer hashIterations;

    @Value("${shiro.password.algorithmName}")
    private String algorithmName;

    @Resource
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        String userId = ExecutionContext.getUserId();
        User user = userService.getUserByUserId(userId);
        model.addAttribute("user", user);
        return "index";
    }


    /**
     * 异步登录
     *
     * @param user 登录用户信息
     */
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

        //获取登录session中的user，将userId、userName存储在本地线程变量中
        User sessionUser = (User) subject.getPrincipal();
        Map<String, String> contextMap = new HashMap<>();
        contextMap.put(ExecutionContext.USER_ID, sessionUser.getUserId());
        contextMap.put(ExecutionContext.USER_NAME, sessionUser.getUserName());
        ExecutionContext.setContextMap(contextMap);

        resultMap.put("url", "index");
        jsonResult.setResultMap(resultMap);
        return jsonResult;
    }


    /**
     * 注册新用户
     *
     * @param regUser 注册表单中参数
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public JsonResult register(User regUser, HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        Map<String, String> resultMap = new HashMap<>();
        jsonResult.setResultMap(resultMap);

        String userName = regUser.getUserName();
        String email = regUser.getEmail();
        String password = regUser.getPassword();
        String rePassword = request.getParameter("rePassword");
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(email)
                || StringUtils.isBlank(password) || StringUtils.isBlank(rePassword)) {
            jsonResult = new JsonResult(JsonStatus.ERROR, "注册表单参数不能为空！");
            return jsonResult;
        }

        if (!StringUtils.equals(password, rePassword)) {
            jsonResult = new JsonResult(JsonStatus.ERROR, "两次输入的密码不一致！");
            return jsonResult;
        }

        if (BooleanUtils.isTrue(userService.existUserName(userName))) {
            jsonResult = new JsonResult(JsonStatus.ERROR, "真不巧，用户名已存在！");
            return jsonResult;
        }

        //保存注册的用户
        String userId = UUIDGenerator.uuid();
        regUser.setUserId(userId);
        regUser.setCreateTime(new Date());
        regUser.setCreateBy(userId);
        regUser.setLastModifyBy(userId);
        regUser.setLastModifyTime(new Date());
        //密码生成加密规则（先md5加密后，在使用salt值后，进行两次md5加密）
        SimpleHash pwdHash = new SimpleHash(algorithmName, password, salt, hashIterations);
        regUser.setPassword(pwdHash.toString());
        userService.saveEntity(regUser);

        resultMap.put("url", "login");
        jsonResult.setResultMap(resultMap);
        return jsonResult;
    }

    /**
     * 登出系统
     */
    @RequestMapping(value = "/logOut")
    public String logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @RequestMapping(value = "/gallery")
    public String gallery(HttpServletRequest request) {
        if (BooleanUtils.isFalse(checkIsPjaxRequest(request))) {
            return "forward:/index";
        }

        return "gallery";
    }

    @RequestMapping(value = "/calendar")
    public String calendar(HttpServletRequest request) {
        if (BooleanUtils.isFalse(checkIsPjaxRequest(request))) {
            return "forward:/index";
        }

        return "calendar";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
}
