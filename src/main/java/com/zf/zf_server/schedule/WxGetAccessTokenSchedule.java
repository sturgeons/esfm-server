package com.zf.zf_server.schedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zf.zf_server.modules.system.entity.SysSource;
import com.zf.zf_server.modules.system.service.SysSourceService;
import com.zf.zf_server.modules.wizard.entity.co.WxProperties;
import com.zf.zf_server.utils.weixin.AccessTokenUtil;
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
    private void test() {
        String accessToken = new AccessTokenUtil(wxProperties.getAppId(), wxProperties.getSecret()).getAccessToken();
        SysSource sysSource = new SysSource();
        sysSource.setUpdateTime(new Date());
        sysSource.setTempValue(accessToken);
        sysSource.setUpdateBy("system");
        sysSourceService.update(sysSource, new QueryWrapper<SysSource>().eq("temp_key", "wx_access_token"));

    }
}
