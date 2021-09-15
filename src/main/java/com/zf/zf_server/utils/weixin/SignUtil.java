package com.zf.zf_server.utils.weixin;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.extension.constant.ResponseInfoConstant;
import com.zf.zf_server.modules.system.service.SysSourceService;
import com.zf.zf_server.modules.wizard.entity.co.WxProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class SignUtil {

    @Autowired
    public SignUtil(SysSourceService sysSourceService, WxProperties wxProperties) {
        this.sysSourceService = sysSourceService;
        this.wxProperties = wxProperties;
    }

    private final SysSourceService sysSourceService;
    private final WxProperties wxProperties;

    public R<Map<String, String>> sign(String url) {

        String accessToken = sysSourceService.getWxAccessToken();

        //获取ticket
        JSONObject jsapiTicketObject = JSONObject.parseObject(HttpUtil.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi"));
        System.out.println(jsapiTicketObject);
        String jsapiTicket = (String) jsapiTicketObject.get("ticket");
        System.out.println("微信返回jsapiTicket" + jsapiTicket);

        Map<String, String> ret = new HashMap<>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature;

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapiTicket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println("string1=" + string1);

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes(StandardCharsets.UTF_8));
            //换取签名
            signature = byteToHex(crypt.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return R.failed(ResponseInfoConstant.INTERNET_CONNECT_FAIL);
        }
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapiTicket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appId", wxProperties.getAppId());  //此时可通过配置引入

        return R.ok(ret);
    }

    /**
     * 随机加密
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 产生随机串--由程序自己随机产生
     */
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * 由程序自己获取当前时间
     */
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
