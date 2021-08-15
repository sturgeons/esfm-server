package com.zf.zf_server.extension.constant;

import org.springframework.beans.factory.annotation.Value;


public interface CommConstant {

    String PREFIX_ADVISOR_BEANS = "prefix_advisor_beans";

    //INFO
    String TOKEN_FAIL = "Token失效,请重新登录";

    @Value("${server.port}")
    String SERVER_PORT = "";

    // token  header 标示key
    String REQUEST_TOKEN="Authorization";

    /**
     * 是否用户已被冻结 1(解冻)正常 0冻结
     */
    int NORMAL = 1;
    int FREEZE = 0;
    String NOT_APPROVE ="未验证" ;

    //token 有限时长 天
    int EXPIRY_INTERVAL = 600;
    // 字典的类型字段
    String DIC_TABLE_FLAG ="table" ;
}
