package com.qs.bluewhale.base;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {

    /**
     * 判断是否为pjax请求（如果是pjax请求，在request的header中会存在"X-PJAX"属性）
     */
    protected boolean checkIsPjaxRequest(HttpServletRequest request) {
        String isPjax = request.getHeader("X-PJAX");
        if (StringUtils.isNotBlank(isPjax) && BooleanUtils.isTrue(Boolean.valueOf(isPjax))) {
            return true;
        }

        return false;
    }
}
