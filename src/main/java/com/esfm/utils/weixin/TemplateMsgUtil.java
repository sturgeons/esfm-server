package com.esfm.utils.weixin;

import cn.hutool.http.HttpUtil;
import com.esfm.extension.api.R;
import com.esfm.modules.system.service.SysSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TemplateMsgUtil {
    @Autowired
    public TemplateMsgUtil(SysSourceService sysSourceService) {
        this.sysSourceService = sysSourceService;
    }

    private final SysSourceService sysSourceService;

    /**
     * 发送模板消息
     *
     * @param jsonStr json字符串
     * @return {ApiResult}
     */
    public R<?> send(String jsonStr) {
        String sendApiUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
        String jsonResult = HttpUtil.post(sendApiUrl + sysSourceService.getWxAccessToken(), jsonStr);
        return R.ok(jsonResult);
    }
}
