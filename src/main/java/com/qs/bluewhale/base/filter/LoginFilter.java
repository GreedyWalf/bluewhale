package com.qs.bluewhale.base.filter;

import com.qs.bluewhale.base.context.ExecutionContext;
import com.qs.bluewhale.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURL().toString();
        System.out.println("-->>过滤器执行了，请求：" + url);
        String contextPath = WebUtils.getContextPath(request);
        request.setAttribute("contextPath", contextPath);
        //过滤器中将已经登录的userId、userName存储在线程变量中，方便在实现业务中获取当前登录的userId、userName
        Subject subject = SecurityUtils.getSubject();
        User sessionUser = (User) subject.getPrincipal();
        if (sessionUser != null) {
            String userId = sessionUser.getUserId();
            String userName = sessionUser.getUserName();
            Map<String, String> contextMap = new HashMap<>();
            contextMap.put(ExecutionContext.USER_ID, userId);
            contextMap.put(ExecutionContext.USER_NAME, userName);
            ExecutionContext.setContextMap(contextMap);
            return true;
        }

        return false;
    }
}
