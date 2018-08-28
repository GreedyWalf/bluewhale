package com.qs.bluewhale.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qs.bluewhale.base.BaseController;
import com.qs.bluewhale.base.context.ExecutionContext;
import com.qs.bluewhale.entity.BillCategory;
import com.qs.bluewhale.entity.BillInfo;
import com.qs.bluewhale.service.BillCategoryService;
import com.qs.bluewhale.service.BillService;
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
@RequestMapping(value = "/bill")
public class BillController extends BaseController {

    @Resource
    private BillCategoryService billCategoryService;

    @Resource
    private BillService billService;

    @RequestMapping(value = "/billList")
    public String billList(HttpServletRequest request, Model model) {
        if (BooleanUtils.isFalse(checkIsPjaxRequest(request))) {
            request.setAttribute("isDispatcher", true);
            return "forward:/index";
        }

        return "bill/billList";
    }

    @RequestMapping(value = "/getBillList")
    @ResponseBody
    public Map<String, Object> getBillList(Page<BillInfo> page) {
        Page<BillInfo> billPage = billService.findBillPage(page);
        PageInfo<BillInfo> pageInfo = new PageInfo<>(billPage);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("count", pageInfo.getTotal());
        dataMap.put("data", pageInfo.getList());
        dataMap.put("code", 0);
        return dataMap;
    }

    @RequestMapping(value = "/addBill")
    public String addBill(Model model) {
        List<BillCategory> categoryList = billCategoryService.getAllBillCategoryList();
        model.addAttribute("categoryList", categoryList);
        return "bill/addBill";
    }


    @RequestMapping(value = "/billCategoryList")
    public String billCategoryList(HttpServletRequest request) {
        if (BooleanUtils.isFalse(checkIsPjaxRequest(request))) {
            request.setAttribute("isDispatcher", true);
            return "forward:/index";
        }

        return "bill/billCategoryList";
    }

    @RequestMapping(value = "/getBillCategoryList")
    @ResponseBody
    public Map<String, Object> getBillCategoryList(Page<BillCategory> page) {
        Page<BillCategory> billCategoryPage = billCategoryService.getBillCategoryList(page);
        PageInfo<BillCategory> pageInfo = new PageInfo<>(billCategoryPage);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("count", pageInfo.getTotal());
        dataMap.put("data", pageInfo.getList());
        dataMap.put("code", 0);
        return dataMap;
    }
}