package com.qs.bluewhale.controller;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/index")
    public String index(){
        return "test/index";
    }

    @RequestMapping(value = "/test2")
    public String test2(HttpServletRequest request){
        String url = request.getRequestURL().toString();
        String isPjax = request.getHeader("X-PJAX");
        if(StringUtils.isBlank(isPjax)){
//            return "redirect:/test/test?preUrl=" + URLEncoder.encode(url);
            return "test/index";
        }

        return "test/test2";
    }

    @RequestMapping(value = "/test3")
    public String test3(HttpServletRequest request){
        String isPjax = request.getHeader("X-PJAX");
        if(StringUtils.isBlank(isPjax)){
            return "test/index";
        }

        return "test/test3";
    }
}
