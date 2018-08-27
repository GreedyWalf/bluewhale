package com.qs.bluewhale.controller;

import com.qs.bluewhale.base.BaseController;
import com.qs.bluewhale.base.context.ExecutionContext;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/bill")
public class BillController extends BaseController {

    @RequestMapping(value = "/billList")
    public String billList(HttpServletRequest request, Model model) {
        if (BooleanUtils.isFalse(checkIsPjaxRequest(request))) {
            request.setAttribute("isDispatcher", true);
            return "forward:/index";
        }

        //获取当前线程变量中的用户
        String userId = ExecutionContext.getUserId();
        //获取当前登录人员的记账记录
        return "bill/billList";
    }
}