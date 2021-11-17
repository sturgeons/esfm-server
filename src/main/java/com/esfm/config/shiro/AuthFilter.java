package com.esfm.config.shiro;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.esfm.utils.HttpContextUtils;
import com.esfm.utils.TokenUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter  extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        //获取请求token
        String token = TokenUtil.getRequestToken((HttpServletRequest) request);
        return new AuthToken(token);

    }
    /**
     * 所有请求全部拒绝访问 --
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return ((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name());
    }

    /**
     * 拒绝访问的请求，会调用onAccessDenied方法，onAccessDenied方法先获取 token
     * 再调用executeLogin方法
     *
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        String token = TokenUtil.getRequestToken((HttpServletRequest) request);
        if (StrUtil.isEmpty(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
            httpResponse.setCharacterEncoding("UTF-8");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 403);
            jsonObject.put("msg", "请先登录");
            httpResponse.getWriter().print(jsonObject.toString());
            return false;
        }
        return executeLogin(request, response);
    }
    /**
     * token失效时候调用
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        httpResponse.setCharacterEncoding("UTF-8");
        JSONObject jsonObject = new JSONObject();
        //处理登录失败的异常
        jsonObject.put("code", 403);
        jsonObject.put("msg", "登录凭证已失效，请重新登录");
        try {
            httpResponse.getWriter().print(jsonObject.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
