package com.esfm.utils;

import cn.hutool.core.util.StrUtil;
import com.esfm.extension.constant.CommConstant;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class TokenUtil {
    public static String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader(CommConstant.REQUEST_TOKEN);
        //如果header中不存在token，则从参数中获取token
        if (StrUtil.isEmpty(token)) {
            token = httpRequest.getParameter(CommConstant.REQUEST_TOKEN);
        }
        return token;
    }
}
