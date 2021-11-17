package com.esfm.schedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esfm.modules.system.entity.SysSource;
import com.esfm.modules.system.service.SysSourceService;
import com.esfm.modules.wizard.entity.co.WxProperties;
import com.esfm.utils.YaoSay;
import com.esfm.utils.weixin.AccessTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.Date;

@Configuration
@EnableScheduling
public class WxGetAccessTokenSchedule {

    @Autowired
    public WxGetAccessTokenSchedule(SysSourceService sysSourceService, WxProperties wxProperties) {
        this.sysSourceService = sysSourceService;
        this.wxProperties = wxProperties;
    }

    private final SysSourceService sysSourceService;
    private final WxProperties wxProperties;

    @Scheduled(cron = "0 0 * * * ?")
    @PostConstruct
    private void getAccess() {
        try {
            String accessToken = new AccessTokenUtil(wxProperties.getAppId(), wxProperties.getSecret()).getAccessToken();
            SysSource sysSource = new SysSource();
            sysSource.setUpdateTime(new Date());
            sysSource.setTempValue(accessToken);
            sysSource.setUpdateBy("system");
            sysSourceService.update(sysSource, new QueryWrapper<SysSource>().eq("temp_key", "wx_access_token"));
        } catch (Exception ex) {
            YaoSay.e("微信获取access_key失败:"+ex.getMessage());
        }
    }
}
