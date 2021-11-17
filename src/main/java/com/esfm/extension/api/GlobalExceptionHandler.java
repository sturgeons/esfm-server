package com.esfm.extension.api;

import com.esfm.utils.YaoSay;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public R<?> authorizationException(HttpServletRequest req, AuthorizationException e) {
        YaoSay.p("权限异常:" + e.getMessage());
        R<?> res = new R<>();
        res.setCode(ApiErrorCode.ROLES_FAILED.getCode())
                .setMsg(ApiErrorCode.ROLES_FAILED.getMsg());
        return res;
    }
    @ExceptionHandler(value = ApiException.class)
    @ResponseBody
    public R<?> ApiException(HttpServletRequest req, AuthorizationException e) {
        YaoSay.p( e.getMessage());
        R<?> res = new R<>();
        res.setCode(ApiErrorCode.ROLES_FAILED.getCode())
                .setMsg(ApiErrorCode.ROLES_FAILED.getMsg());
        return res;
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R<?> Exception(Exception ex) {
        ex.printStackTrace();
        YaoSay.e("Exception " + ex.getMessage());
        return R.failed(ApiErrorCode.FAILED);

    }
}