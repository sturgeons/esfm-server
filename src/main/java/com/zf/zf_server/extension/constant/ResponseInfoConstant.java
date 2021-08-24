package com.zf.zf_server.extension.constant;

public interface ResponseInfoConstant {


    /**
     * 是否用户已被冻结 1(解冻)正常 2冻结
     */
    String ERROR_LOGIN_INFO = "错误的登录信息！";
    String ERROR_USERNAME_OR_PASSWORD_IS_EMPTY = "用户或密码为空！";
    String ERROR_LOGIN_FREEZE ="账号已经冻结！" ;
    String ACCOUNT_NOT_APPROVE = "账户还没有通过系统验证，等待管理员通过。";
    String NO_LOGIN_INFO = "无登录信息";
    String OPERATE_SUCCESS = "操作成功";
    String OPERATE_FAIL ="操作失败";
    String NOT_LPA_AUDITOR = "你不是审核员";
    String STRING_IS_BLANK = "必要的字符串为空！";
    String HAVE_SAME_ITEM = "已经存在重复的数值。";
    String NOT_SAME_OLD_PASSWORD = "与旧密码不相同。";
}
