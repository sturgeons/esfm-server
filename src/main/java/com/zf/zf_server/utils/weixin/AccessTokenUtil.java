package com.zf.zf_server.utils.weixin;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

public class AccessTokenUtil {
    private final String ACCESS_TOKEN_URL;

    public AccessTokenUtil(String appId, String secret) {
        this.ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + secret + "";
    }

    public String getAccessToken() {
//        System.out.println("微信授权链接" + ACCESS_TOKEN_URL);
        //获取JS-SDK的 accessToken 注意:其与获取网页授权的accessToken不同
        JSONObject accessTokenObject = JSONObject.parseObject(HttpUtil.get(ACCESS_TOKEN_URL));
        System.out.println("微信获取accessToken");
        System.out.println(accessTokenObject);
        String accessToken = (String) accessTokenObject.get("access_token");
//        System.out.println("微信返回accessToken" + accessToken);
        return accessToken;
    }
}
